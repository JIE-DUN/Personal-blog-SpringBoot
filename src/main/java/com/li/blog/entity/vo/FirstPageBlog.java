package com.li.blog.entity.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 博客首页数据实体类
 * 使用ElasticSearch搜索
 */
@Document(indexName="li", type="blog_springboot")
public class FirstPageBlog implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7818298491033164356L;
	//Blog
	@Id
    private Long id;
	//字段类型为Text，存储
	@Field(type=FieldType.Text,store= true)
    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private String description;
    /**内容*/
    //字段类型为Text，存储
    @Field(type=FieldType.Text,store= true)
    private String content;

    //Type
    private String typeName;

    //User
    private String nickname;
    private String avatar;

    public FirstPageBlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "FirstPageBlog [id=" + id + ", title=" + title + ", firstPicture=" + firstPicture + ", views=" + views
				+ ", commentCount=" + commentCount + ", updateTime=" + updateTime + ", description=" + description
				+ ", content=" + content + ", typeName=" + typeName + ", nickname=" + nickname + ", avatar=" + avatar
				+ "]";
	}

	
}