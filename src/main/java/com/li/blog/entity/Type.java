package com.li.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类实体类
 */
public class Type implements Serializable  {

    private Long id;
    /**类名*/
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + ", blogs=" + blogs + "]";
	}

}