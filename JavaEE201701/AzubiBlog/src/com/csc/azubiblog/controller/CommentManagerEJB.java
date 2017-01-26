package com.csc.azubiblog.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.csc.azubiblog.controller.dao.CommentDao;
import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Comment;

@Stateless
@Remote(CommentManager.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CommentManagerEJB implements CommentManager{

	@EJB //Rufe eine Entity Java Beans welche sich innerhalb meines Projektes befindet
	private CommentDao m_commentDao;
	
	public Comment insertComment(Comment comment, long blogId) {
		return (Comment) m_commentDao.insert(comment);
	}

	public List<Comment> getCommentsByBlog(Blog blogId) {
		return (List<Comment>) m_commentDao.getCommentsByBlog(blogId);
	}

}
