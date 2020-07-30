package com.li.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.li.blog.entity.Comment;

/**
 * 评论持久层接口
 */
public interface CommentDao {

    //根据创建时间倒序来排
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    //查询一级回复
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);

    //查询二级回复
    List<Comment> findByBlogIdAndReplayId(@Param("blogId") Long blogId,@Param("childId") Long childId);

    //查询父级对象
//    Comment findByParentCommentId(Long parentCommentId);

    //添加一个评论
    int saveComment(Comment comment);

    //删除评论
    void deleteComment(Long id);

}