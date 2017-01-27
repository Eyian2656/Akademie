package com.csc.azubiblog.controller;

import java.util.List;

import javax.ejb.Remote;

import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Comment;

/**
 * @author NFriese, FKohlhaas
 * 
 * Global comment-administration methods
 */
@Remote
public interface CommentManager {

	/**
	 * Method for inserting a new comment
	 * @param comment - The comment to insert
	 * @param blogId - The numeric unique blog id, the comment depends on
	 * @return Comment - For JUnit Tests
	 */
	public Comment insertComment(Comment comment, long blogId);
	
	/**
	 * Method for selecting a list of comments, using their unique blog id
	 * @param blogId - Numeric unique blog id
	 * @return List<Comment> 
	 */
	public List<Comment> getCommentsByBlog(Blog blogId);
	
}
