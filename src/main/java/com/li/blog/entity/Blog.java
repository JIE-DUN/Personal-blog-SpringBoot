package com.li.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客实体类
 */
public class Blog implements Serializable {

	private Long id;
	/** 主键 */
	private String title;
	/** 内容 */
	private String content;
	/** 首图 */
	private String firstPicture;
	/** 标记 */
	private String flag;
	/** 浏览次数 */
	private Integer views;

	private Integer commentCount;

	/** 赞赏是否开启 */
	private boolean appreciation;
	/** 转载声明是否开启 */
	private boolean shareStatement;
	/** 评论是否开启 */
	private boolean commentabled;
	/** 是否发布 */
	private boolean published;
	/** 是否推荐 */
	private boolean recommend;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	/** 类型id */
	private Long typeId;
	/** 用户id */
	private Long userId;

	/** 描述 */
	private String description;
	private Type type;
	private User user;
	private List<Comment> comments = new ArrayList<>();

	public Blog() {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFirstPicture() {
		return firstPicture;
	}

	public void setFirstPicture(String firstPicture) {
		this.firstPicture = firstPicture;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public boolean isAppreciation() {
		return appreciation;
	}

	public void setAppreciation(boolean appreciation) {
		this.appreciation = appreciation;
	}

	public boolean isShareStatement() {
		return shareStatement;
	}

	public void setShareStatement(boolean shareStatement) {
		this.shareStatement = shareStatement;
	}

	public boolean isCommentabled() {
		return commentabled;
	}

	public void setCommentabled(boolean commentabled) {
		this.commentabled = commentabled;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public boolean isRecommend() {
		return recommend;
	}

	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", firstPicture=" + firstPicture
				+ ", flag=" + flag + ", views=" + views + ", commentCount=" + commentCount + ", appreciation="
				+ appreciation + ", shareStatement=" + shareStatement + ", commentabled=" + commentabled
				+ ", published=" + published + ", recommend=" + recommend + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", typeId=" + typeId + ", userId=" + userId + ", description="
				+ description + ", type=" + type + ", user=" + user + ", comments=" + comments + "]";
	}

}