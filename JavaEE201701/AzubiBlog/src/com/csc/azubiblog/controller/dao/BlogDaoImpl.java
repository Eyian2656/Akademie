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

/**
 * @author NFriese, FKohlhaas
 * 
 * Implementation of blog data access using the BlogDao interface
 */
@Stateless
public class BlogDaoImpl implements BlogDao{
	
	/**
	 * Calling a entityManager object from EntityManager
	 */
	@PersistenceContext(unitName="ExampleDS")
	private EntityManager m_entityManager;	
	
	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.dao.Dao#find(long)
	 */
	@Override
	public ValueObject find(long id) {
		return m_entityManager.find(Blog.class, id);
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.dao.Dao#update(com.csc.azubiblog.model.ValueObject)
	 */
	@Override
	public ValueObject update(ValueObject value) {
		Blog blog = m_entityManager.merge((Blog)value);
		m_entityManager.persist(blog);
		return blog;
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.dao.Dao#insert(com.csc.azubiblog.model.ValueObject)
	 */
	@Override
	public ValueObject insert(ValueObject value) {
		m_entityManager.persist((Blog)value);
		return (Blog)value;
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.dao.Dao#delete(com.csc.azubiblog.model.ValueObject)
	 */
	@Override
	public boolean delete(ValueObject value) {
		Blog blog = m_entityManager.merge((Blog)value);
		m_entityManager.remove(blog);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.dao.BlogDao#getBlogsByAuthor(java.lang.Long)
	 */
	@Override
	public List<Blog> getBlogsByAuthor(Long author){
		TypedQuery<Blog> query = m_entityManager.createNamedQuery(Blog.GET_BLOGS_BY_AUTHOR, Blog.class);
		query.setParameter("author", author);
		
		return query.getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.dao.BlogDao#getBlogsByCategory(com.csc.azubiblog.model.Category)
	 */
	@Override
	public List<Blog> getBlogsByCategory(Category category){
		TypedQuery<Blog> query = m_entityManager.createNamedQuery(Blog.GET_BLOGS_BY_CATEGORY, Blog.class);
		query.setParameter("category", category);
		
		return query.getResultList();
	}
}
