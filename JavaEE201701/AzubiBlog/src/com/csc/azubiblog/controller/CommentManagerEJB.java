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

/**
 * @author NFriese, FKohlhaas
 *
 * This class will afford the server, to use the blog management methods.
 * Its based on the Java Bean standard.
 */
@Stateless
@Remote(CommentManager.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CommentManagerEJB implements CommentManager{

	/**
	 * Creating a data access object from BlogDao
	 */ 
	@EJB //Requesting a Entitiy Java Beans which is placed in this project
	private CommentDao m_commentDao;
	
	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.CommentManager#insertComment(com.csc.azubiblog.model.Comment, long)
	 */
	public Comment insertComment(Comment comment, long blogId) {
		return (Comment) m_commentDao.insert(comment);
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.CommentManager#getCommentsByBlog(com.csc.azubiblog.model.Blog)
	 */
	public List<Comment> getCommentsByBlog(Blog blogId) {
		return (List<Comment>) m_commentDao.getCommentsByBlog(blogId);
	}

}
