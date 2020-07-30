package com.li.blog.service.Impl;

import com.li.blog.dao.FriendLinkDao;
import com.li.blog.service.FriendLinkService;
import com.li.blog.entity.FriendLink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链业务层接口实现类
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkDao friendLinkDao;

	/**查询所有友链*/
    @Override
    public List<FriendLink> listFriendLink() {
        return friendLinkDao.listFriendLink();
    }

    /**添加友链*/
    @Override
    public int saveFriendLink(FriendLink friendLink) {
        return friendLinkDao.saveFriendLink(friendLink);
    }

    /**根据id查询友链*/
    @Override
    public FriendLink getFriendLink(Long id) {
        return friendLinkDao.getFriendLink(id);
    }

    /**根据网址查询友链*/
    @Override
    public FriendLink getFriendLinkByBlogaddress(String blogaddress) {
        return friendLinkDao.getFriendLinkByBlogaddress(blogaddress);
    }

    /**编辑修改友链*/
    @Override
    public int updateFriendLink(FriendLink friendLink) {
        return friendLinkDao.updateFriendLink(friendLink);
    }

    /**删除友链*/
    @Override
    public void deleteFriendLink(Long id) {
        friendLinkDao.deleteFriendLink(id);
    }


}