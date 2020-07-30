package com.li.blog.dao;

import java.util.List;

import com.li.blog.entity.Picture;

/**
 * 照片墙持久层接口
 */
public interface PictureDao {

	/**查询所有照片*/
    List<Picture> listPicture();

    /**添加图片*/
    int savePicture(Picture picture);

    /**根据id查询照片*/
    Picture getPicture(Long id);

    /**编辑修改相册*/
    int updatePicture(Picture picture);

    /**删除照片*/
    void deletePicture(Long id);

}