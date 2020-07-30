package com.li.blog.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.li.blog.dao.TypeDao;
import com.li.blog.entity.Type;
import com.li.blog.service.TypeService;

/**
 * 文章分类业务层接口实现类
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    /**新增保存分类*/
    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    /**根据id查询分类*/
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    /**查询所有分类*/
    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    /**分类页面查询*/
    @Override
    public List<Type> getAllTypeAndBlog() {
        return typeDao.getAllTypeAndBlog();
    }

    /**根据分类名称来查询*/
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    /**编辑修改分类*/
    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    /**删除分类*/
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }



}