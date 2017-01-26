package com.csc.azubiblog.controller.dao;

import java.util.List;

import javax.ejb.Local;

import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Comment;

@Local
public interface CommentDao extends Dao{

	List<Comment> getCommentsByBlog(Blog blog);

}
