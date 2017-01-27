package com.csc.azubiblog.controller.dao;

import java.util.List;

import javax.ejb.Local;

import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Comment;

/**
 * @author NFriese, FKohlhaas
 * 
 * Global comment data access-administration methods
 */
@Local
public interface CommentDao extends Dao{

	/**
	 * Method for selecting a list of Comments, using a blog 
	 * @param blog - s.a.
	 * @return List<Comment> s.a.
	 */
	List<Comment> getCommentsByBlog(Blog blog);

}
