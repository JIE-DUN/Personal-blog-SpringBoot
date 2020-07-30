package com.li.blog.dao;

import java.util.List;

import com.li.blog.entity.FriendLink;

/**
 * 友链持久层接口
 */
public interface FriendLinkDao {

	/**查询所有友链*/
    List<FriendLink> listFriendLink();

    /**添加友链*/
    int saveFriendLink(FriendLink friendLink);

    /**根据id查询友链*/
    FriendLink getFriendLink(Long id);

    /**根据网址查询友链*/
    FriendLink getFriendLinkByBlogaddress(String blogaddress);

    /**编辑修改友链*/
    int updateFriendLink(FriendLink friendLink);

    /**删除友链*/
    void deleteFriendLink(Long id);

}