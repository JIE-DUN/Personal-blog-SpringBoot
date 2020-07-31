package com.li.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(value = "com.li.blog.dao")
@SpringBootApplication
@EnableTransactionManagement
public class PersonalBlogSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalBlogSpringBootApplication.class, args);
	}

}
