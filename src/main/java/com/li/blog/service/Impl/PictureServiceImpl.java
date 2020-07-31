package com.li.blog.service.Impl;

import com.li.blog.dao.PictureDao;
import com.li.blog.service.PictureService;
import com.li.blog.entity.Picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 照片墙业务层接口实现类
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    /**查询所有照片*/
    @Override
    public List<Picture> listPicture() {
        return pictureDao.listPicture();
    }

    /**添加图片*/
    @Override
    @Transactional
    public int savePicture(Picture picture) {
        return pictureDao.savePicture(picture);
    }

    /**根据id查询照片*/
    @Override
    public Picture getPicture(Long id) {
        return pictureDao.getPicture(id);
    }

    /**编辑修改相册*/
    @Override
    @Transactional
    public int updatePicture(Picture picture) {
        return pictureDao.updatePicture(picture);
    }

    /**删除照片*/
    @Override
    @Transactional
    public void deletePicture(Long id) {
        pictureDao.deletePicture(id);
    }

}