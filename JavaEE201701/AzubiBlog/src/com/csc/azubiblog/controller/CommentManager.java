package com.csc.azubiblog.controller;

import java.util.List;

import javax.ejb.Remote;

import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Comment;

@Remote
public interface CommentManager {

	public Comment insertComment(Comment comment, long blogId);
	
	public List<Comment> getCommentsByBlog(Blog blogId);
	
}
