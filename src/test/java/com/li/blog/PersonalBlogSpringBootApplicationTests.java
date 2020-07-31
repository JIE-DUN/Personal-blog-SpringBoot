package com.li.blog;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.li.blog.dao.BlogDao;
import com.li.blog.entity.vo.FirstPageBlog;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalBlogSpringBootApplicationTests {

	@Autowired
	BlogDao blogDao;
	
	@Autowired
	ElasticsearchTemplate template ;
	
	@Test
	public void test() {
		FirstPageBlog firstPageBlogById = blogDao.getFirstPageBlogById(3L);
		System.out.println(firstPageBlogById.toString());
	}
	
	/**
	 * 创建Elasticsearch里的索引
	 */
	@Test
	public void createIndex() throws Exception {
		template.createIndex(FirstPageBlog.class);
	}
	
	
}
