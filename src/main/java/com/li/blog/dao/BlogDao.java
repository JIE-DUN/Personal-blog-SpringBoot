package com.li.blog.dao;

import java.util.List;

import com.li.blog.entity.Blog;
import com.li.blog.entity.vo.BlogQuery;
import com.li.blog.entity.vo.DetailedBlog;
import com.li.blog.entity.vo.FirstPageBlog;
import com.li.blog.entity.vo.RecommendBlog;
import com.li.blog.entity.vo.SearchBlog;
import com.li.blog.entity.vo.ShowBlog;

/**
 * 博客持久层接口
 */
public interface BlogDao {

	/**查询编辑修改文章*/
    ShowBlog getBlogById(Long id);

    /**查询所有文章*/
    List<Blog> getAllBlog();

    /**查询文章管理列表*/
    List<BlogQuery> getAllBlogQuery();

    /**新增保存文章*/
    int saveBlog(Blog blog);

    /**编辑修改文章*/
    int updateBlog(ShowBlog showBlog);

    /**删除文章*/
    void deleteBlog(Long id);

    /***/
    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    /**得到所有FirstPageBlog用于首页展示*/
    List<FirstPageBlog> getFirstPageBlog();

    /**根据id查询FirstPageBlog用于搜索*/
    FirstPageBlog getFirstPageBlogById(Long id);

    /**推荐文章*/
    List<RecommendBlog> getAllRecommendBlog();

//    List<FirstPageBlog> getNewBlog();

    /**搜索文章*/
    List<FirstPageBlog> getSearchBlog(String query);

    /**博客详情查询*/
    DetailedBlog getDetailedBlog(Long id);

    /**文章访问自增*/
    int updateViews(Long id);

//    根据博客id查询出评论数量
    int getCommentCountById(Long id);

    /**分类页面查询*/
    List<FirstPageBlog> getByTypeId(Long typeId);

    /**统计博客信息*/
    Integer getBlogTotal();

    /***/
    Integer getBlogViewTotal();

    /***/
    Integer getBlogCommentTotal();

    /***/
    Integer getBlogMessageTotal();
}