package com.li.blog.service.Impl;

import com.li.blog.dao.UserDao;
import com.li.blog.service.UserService;
import com.li.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务层接口实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**查询用户*/
	@Override
	public User getByEntity(User user) {
		return userDao.getByEntity(user);
	}
}