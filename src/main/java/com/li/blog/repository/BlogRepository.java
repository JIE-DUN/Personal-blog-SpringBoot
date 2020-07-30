package com.li.blog.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.li.blog.entity.vo.FirstPageBlog;


/**
 * ElasticSearch的Repository
 *
 */
public interface BlogRepository extends ElasticsearchRepository<FirstPageBlog, Long>{
//	public List<FirstPageBlog> findByTitleOrContent(String title,String content);
	
	public List<FirstPageBlog> findByTitleOrContent(String title,String content,Pageable pageable);

}
