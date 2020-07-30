package com.li.blog.dao;

import org.apache.ibatis.annotations.Param;

import com.li.blog.entity.User;

/**
 * 用户持久层接口
 */
public interface UserDao {

    /**查询用户*/
	User getByEntity(User user);
}