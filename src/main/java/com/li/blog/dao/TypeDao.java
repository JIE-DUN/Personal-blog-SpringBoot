package com.li.blog.dao;

import java.util.List;

import com.li.blog.entity.Type;

/**
 * 文章分类持久层接口
 */
public interface TypeDao {

	/**新增保存分类*/
    int saveType(Type type);

    /**根据id查询分类*/
    Type getType(Long id);

    /**查询所有分类*/
    List<Type> getAllType();

    /**分类页面查询*/
    List<Type> getAllTypeAndBlog();

    /**根据分类名称来查询*/
    Type getTypeByName(String name);

    /**编辑修改分类*/
    int updateType(Type type);

    /**删除分类*/
    void deleteType(Long id);


}