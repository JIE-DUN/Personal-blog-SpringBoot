package com.li.blog.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * 友链实体类
 */
public class FriendLink implements Serializable  {

    private Long id;
    /**友链名称*/
    private String blogname;
    /**友链地址*/
    private String blogaddress;
    /**友链图片*/
    private String pictureaddress;
    private Date createTime;

    public FriendLink() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogname() {
        return blogname;
    }

    public void setBlogname(String blogname) {
        this.blogname = blogname;
    }

    public String getBlogaddress() {
        return blogaddress;
    }

    public void setBlogaddress(String blogaddress) {
        this.blogaddress = blogaddress;
    }

    public String getPictureaddress() {
        return pictureaddress;
    }

    public void setPictureaddress(String pictureaddress) {
        this.pictureaddress = pictureaddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "FriendLink [id=" + id + ", blogname=" + blogname + ", blogaddress=" + blogaddress + ", pictureaddress="
				+ pictureaddress + ", createTime=" + createTime + "]";
	}

}