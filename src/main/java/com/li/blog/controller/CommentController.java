package com.li.blog.controller;

import com.li.blog.entity.Comment;
import com.li.blog.entity.User;
import com.li.blog.entity.vo.DetailedBlog;
import com.li.blog.service.BlogService;
import com.li.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 评论控制器
 */
@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private BlogService blogService;

	//这个直接写成常数，主要作用是游客发表评论的时候用到的头像
	@Value("${comment.avatar}")
	private String avatar;

	// 查询评论列表
	@GetMapping("/comments/{blogId}")
	public String comments(@PathVariable Long blogId, Model model) {
		//根据博客id查询评论列表
		List<Comment> comments = commentService.listCommentByBlogId(blogId);
		model.addAttribute("comments", comments);
		return "blog :: commentList";
	}

	// 新增评论
	@PostMapping("/comments")
	public String post(Comment comment, HttpSession session, Model model) {
		Long blogId = comment.getBlogId();
		
		//这里用shiro代替验证////////////////////////////////
		User user = (User) session.getAttribute("user");
		if (user != null) {
			comment.setAvatar(user.getAvatar());
			comment.setAdminComment(true);
		} else {
			// 设置头像
			comment.setAvatar(avatar);
		}
		////////////////////////////////////////////////////////////////
		
		//得到父评论id
		if (comment.getParentComment().getId() != null) {
			comment.setParentCommentId(comment.getParentComment().getId());
		}
		commentService.saveComment(comment);
		List<Comment> comments = commentService.listCommentByBlogId(blogId);
		model.addAttribute("comments", comments);
		return "blog :: commentList";
	}

	// 删除评论
	//TODO  改为DeleteMapping
	//这个删除评论不应该的登录后才能使用吗
	@GetMapping("/comment/{blogId}/{id}/delete")
	public String delete(@PathVariable Long blogId, @PathVariable Long id, Comment comment,
			RedirectAttributes attributes, Model model) {
		//删除评论，并重新该条博客的总查询评论条数，这里可以直接用{blogId}吧
		commentService.deleteComment(comment, id);
		DetailedBlog detailedBlog = blogService.getDetailedBlog(blogId);
		List<Comment> comments = commentService.listCommentByBlogId(blogId);
		model.addAttribute("blog", detailedBlog);
		model.addAttribute("comments", comments);
		return "blog";
	}

}