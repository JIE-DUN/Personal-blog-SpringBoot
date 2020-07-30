package com.li.blog.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

/**
 * 
 * ElasticSearchConfig配置
 *
 */
@Configuration
public class ElasticSearchConfig {
	
	/**
	 * System.setProperty("es.set.netty.runtime.available.processors", "false");
	 * 解决同时引用redis与es启动时报错
	 */
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}