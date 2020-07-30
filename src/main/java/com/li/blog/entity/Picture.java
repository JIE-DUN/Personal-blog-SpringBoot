package com.li.blog.entity;

import java.io.Serializable;

/**
 * 照片墙实体类
 */
public class Picture implements Serializable  {

    private Long id;
    /**图片名称*/
    private String picturename;
    /**图片拍摄地点*/
    private String picturetime;
    /**图片地址*/
    private String pictureaddress;
    /**图片描述*/
    private String picturedescription;

    public Picture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicturename() {
        return picturename;
    }

    public void setPicturename(String picturename) {
        this.picturename = picturename;
    }

    public String getPicturetime() {
        return picturetime;
    }

    public void setPicturetime(String picturetime) {
        this.picturetime = picturetime;
    }

    public String getPictureaddress() {
        return pictureaddress;
    }

    public void setPictureaddress(String pictureaddress) {
        this.pictureaddress = pictureaddress;
    }

    public String getPicturedescription() {
        return picturedescription;
    }

    public void setPicturedescription(String picturedescription) {
        this.picturedescription = picturedescription;
    }

	@Override
	public String toString() {
		return "Picture [id=" + id + ", picturename=" + picturename + ", picturetime=" + picturetime
				+ ", pictureaddress=" + pictureaddress + ", picturedescription=" + picturedescription + "]";
	}
 
}