package com.csc.azubiblog.controller.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Category;
import com.csc.azubiblog.model.User;
import com.csc.azubiblog.model.ValueObject;

@Stateless
public class BlogDaoImpl implements BlogDao{

	@PersistenceContext(unitName="ExampleDS")
	private EntityManager m_entityManager;	
	
	@Override
	public ValueObject find(long id) {
		return m_entityManager.find(Blog.class, id);
	}

	@Override
	public ValueObject update(ValueObject value) {
		Blog blog = m_entityManager.merge((Blog)value);
		m_entityManager.persist(blog);
		return blog;
	}

	@Override
	public ValueObject insert(ValueObject value) {
		m_entityManager.persist((Blog)value);
		return (Blog)value;
	}

	@Override
	public boolean delete(ValueObject value) {
		Blog blog = m_entityManager.merge((Blog)value);
		m_entityManager.remove(blog);
		return true;
	}

	@Override
	public List<Blog> getBlogsByAuthor(Long author){
		TypedQuery<Blog> query = m_entityManager.createNamedQuery(Blog.GET_BLOGS_BY_AUTHOR, Blog.class);
		query.setParameter("author", author);
		
		return query.getResultList();
	}
	
	@Override
	public List<Blog> getBlogsByCategory(Category category){
		TypedQuery<Blog> query = m_entityManager.createNamedQuery(Blog.GET_BLOGS_BY_CATEGORY, Blog.class);
		query.setParameter("category", category);
		
		return query.getResultList();
	}
}
