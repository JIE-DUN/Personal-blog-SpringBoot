package com.li.blog.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.li.blog.NotFoundException;
import com.li.blog.dao.BlogDao;
import com.li.blog.entity.Blog;
import com.li.blog.entity.vo.BlogQuery;
import com.li.blog.entity.vo.DetailedBlog;
import com.li.blog.entity.vo.FirstPageBlog;
import com.li.blog.entity.vo.RecommendBlog;
import com.li.blog.entity.vo.SearchBlog;
import com.li.blog.entity.vo.ShowBlog;
import com.li.blog.repository.BlogRepository;
import com.li.blog.service.BlogService;
import com.li.blog.util.MarkdownUtils;

/**
 * 博客列表业务层接口实现类
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;
    
    /**引入RedisTemplate*/
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
	BlogRepository blogRepository;
    
    /**
     * 根据id查询文章
     */
    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    /**
     *  查询文章管理列表
     */
    @Override
    public List<BlogQuery> getAllBlog() {
        return blogDao.getAllBlogQuery();
    }

    /**
     * 新增博文
     */
    @Override
    @Transactional
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        int a = blogDao.saveBlog(blog);
        //删除redis里对应数据
        redisTemplate.delete("allFirstPageBlog");
        redisTemplate.delete("allRecommendedBlog");
        
        //如果不是草稿,就在新增博文的时候就把FirstPageBlog放进Elasticsearch
        if(blog.isPublished()){
	        FirstPageBlog firstPageBlog = blogDao.getFirstPageBlogById(blog.getId());
	        blogRepository.save(firstPageBlog);
        }
        
        return a;
    }

    /**
     * 更新文章
     */
    @Override
    @Transactional
    public int updateBlog(ShowBlog showBlog) {
    	//更新文章更新时间
        showBlog.setUpdateTime(new Date());
        int a = blogDao.updateBlog(showBlog);
        //删除redis里对应数据
        redisTemplate.delete("allFirstPageBlog");
        redisTemplate.delete("allRecommendedBlog");
        
        //同步更新Elasticsearch里对应的数据
        FirstPageBlog firstPageBlog = blogDao.getFirstPageBlogById(showBlog.getId());
        blogRepository.save(firstPageBlog);
        
        return a;
    }

    /**
     * 删除博客
     */
    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
        //删除redis里对应数据
        redisTemplate.delete("allFirstPageBlog");
        redisTemplate.delete("allRecommendedBlog");
        //同步删除Elasticsearch里对应的数据
        blogRepository.deleteById(id);
    }

    /**
     * 根据文章标题、类型、推荐查询文章 
     * 用于后台查找
     */
    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    /**
     * 查询首页博客，根据更新时间排序
     */
    @Override
	public List<FirstPageBlog> getAllFirstPageBlog() {
    	//增加key值在redis里的可读性，也可以在RedisConfig里把串行化方法变为json格式进行配置
//    	RedisSerializer redisSerializer = new StringRedisSerializer();
//    	redisTemplate.setKeySerializer(redisSerializer);
    	
		// 先查询redis里有没有首页的博客的数据
		List<FirstPageBlog> list = (List<FirstPageBlog>) redisTemplate.opsForValue().get("allFirstPageBlog");
		// 如果没有就连接数据库做一次查询
		// 这里连续两次判断视为了解决内存穿透问题，防止第一波查询的时候，redis里没有数据，然后所有都直接查询数据库了
		if (list == null) {
			synchronized (this) {
				list = (List<FirstPageBlog>) redisTemplate.opsForValue().get("allFirstPageBlog");
				if (list == null) {
//					System.out.println("------------------redis检测是否有allFirstPageBlog----------------------");
					// 查询
					list = blogDao.getFirstPageBlog();
					// //并把查询出来的数据放到redis,设置24小时过期
					// redisTemplate.opsForValue().set("timeout", list,1,TimeUnit.HOURS);
					// 并把查询出来的数据放到redis，不设置过期时间了
					redisTemplate.opsForValue().set("allFirstPageBlog", list);
				}
			}
		}
		return list;
	}

    /**
     * 查询所有推荐博客
     * redis缓存
     */
	@Override
	public List<RecommendBlog> getRecommendedBlog() {
		// 先查询redis里有没有推荐博客的数据
		List<RecommendBlog> list = (List<RecommendBlog>) redisTemplate.opsForValue().get("allRecommendedBlog");
		// 如果没有就连接数据库做一次查询
		// 这里连续两次判断视为了解决内存穿透问题，防止第一波查询的时候，redis里没有数据，然后所有都直接查询数据库了
		if (list == null) {
			synchronized (this) {
				list = (List<RecommendBlog>) redisTemplate.opsForValue().get("allRecommendedBlog");
				if(list == null){
					System.out.println("------------------redis检测是否有allRecommendedBlog----------------------");	
					// 查询
					list = blogDao.getAllRecommendBlog();
					// //并把查询出来的数据放到redis,设置24小时过期
					// redisTemplate.opsForValue().set("timeout", list, 1,TimeUnit.HOURS);
					// 并把查询出来的数据放到redis，不设置过期时间了
					redisTemplate.opsForValue().set("allRecommendedBlog", list);
				}
			}
		}
		return list;
	}

//    @Override
//    public List<FirstPageBlog> getNewBlog() {
//        return blogDao.getNewBlog();
//    }

    /**
     * 根据关键字搜索文章标题或者内容
     * 用于前台(首页)查找
     * @param query 关键字
     */
    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
    	Sort sort = new Sort(Sort.Direction.DESC,"id");
		Pageable pageable = PageRequest.of(0, 1000, sort);
		List<FirstPageBlog> findByTitleOrContent = blogRepository.findByTitleOrContent(query, query,pageable);
        return findByTitleOrContent;
    }

    /**
     * 根据id查询博客详情
     * 
     */
    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        System.out.println(id+"--------------------------------------------");
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        //使用MarkdownUtils将内容转换成HTML,再赋值回对象
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        //文章访问数量自增
        blogDao.updateViews(id);
        //文章评论数量更新
        blogDao.getCommentCountById(id);
        return detailedBlog;
    }


    /**
     * 根据博客类型的id查询博客
     */
    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    /**
     * 查询博客数量
     */
    @Override
    public Integer getBlogTotal() {
        return blogDao.getBlogTotal();
    }

    /**
     * 查询博客访客数量
     */
    @Override
    public Integer getBlogViewTotal() {
        return blogDao.getBlogViewTotal();
    }

    /**
     * 查询博客评论数量
     * 这个可以加载到redis
     */
    @Override
    public Integer getBlogCommentTotal() {
        return blogDao.getBlogCommentTotal();
    }

    /**
     * 查询留言数量
     */
    @Override
    public Integer getBlogMessageTotal() {
        return blogDao.getBlogMessageTotal();
    }
}