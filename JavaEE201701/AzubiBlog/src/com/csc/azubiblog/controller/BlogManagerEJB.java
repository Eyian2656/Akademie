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

@Stateless
@Remote(BlogManager.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BlogManagerEJB implements BlogManager{
	
	@EJB
	private BlogDao m_blogDao;
	
	public Blog insertBlog(Blog blog) {
		return (Blog) m_blogDao.insert(blog);
	}

	public Blog getBlog(long id) {
		return (Blog) m_blogDao.find(id);
	}

	public List<Blog> getBlogsByAuthor(Long user) {
		return m_blogDao.getBlogsByAuthor(user);
	}

	public List<Blog> getBlogsByCategory(Category category) {
		return m_blogDao.getBlogsByCategory(category);
	}

	public boolean deleteBlog(Blog blog) {
		return m_blogDao.delete(blog);
	}

	public Blog updateBlog(Blog blog) {
		return (Blog) m_blogDao.update(blog);
	}	
}
