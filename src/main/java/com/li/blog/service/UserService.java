package com.li.blog.service;

import com.li.blog.entity.User;

/**
 * 用户业务层接口
 */
public interface UserService {

    /**查询用户*/
    User getByEntity(User user);

}