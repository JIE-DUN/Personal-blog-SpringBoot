package com.li.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.li.blog.dao")
@SpringBootApplication
public class PersonalBlogSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalBlogSpringBootApplication.class, args);
	}

}
