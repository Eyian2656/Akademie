package com.csc.azubiblog.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.csc.azubiblog.controller.dao.BlogDao;
import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Category;

/**
 * @author NFriese, FKohlhaas
 *
 * This class will afford the server, to use the blog management methods.
 * Its based on the Java Bean standard.
 */
@Stateless
@Remote(BlogManager.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BlogManagerEJB implements BlogManager{
	
	/**
	 * Creating a data access object from BlogDao
	 */ 
	@EJB //Requesting a Entitiy Java Beans which is placed in this project
	private BlogDao m_blogDao;
	
	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.BlogManager#insertBlog(com.csc.azubiblog.model.Blog)
	 */
	public Blog insertBlog(Blog blog) {
		return (Blog) m_blogDao.insert(blog);
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.BlogManager#getBlog(long)
	 */
	public Blog getBlog(long id) {
		return (Blog) m_blogDao.find(id);
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.BlogManager#getBlogsByAuthor(java.lang.Long)
	 */
	public List<Blog> getBlogsByAuthor(Long user) {
		return m_blogDao.getBlogsByAuthor(user);
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.BlogManager#getBlogsByCategory(com.csc.azubiblog.model.Category)
	 */
	public List<Blog> getBlogsByCategory(Category category) {
		return m_blogDao.getBlogsByCategory(category);
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.BlogManager#deleteBlog(com.csc.azubiblog.model.Blog)
	 */
	public boolean deleteBlog(Blog blog) {
		return m_blogDao.delete(blog);
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.BlogManager#updateBlog(com.csc.azubiblog.model.Blog)
	 */
	public Blog updateBlog(Blog blog) {
		return (Blog) m_blogDao.update(blog);
	}	
}
