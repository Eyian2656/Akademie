package com.csc.azubiblog.controller.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Comment;
import com.csc.azubiblog.model.ValueObject;

/**
 * @author NFriese, FKohlhaas
 * 
 * Implementation of comment data access using the CommentDao interface
 */
@Stateless
public class CommentDaoImpl implements CommentDao{
	
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
		return m_entityManager.find(Comment.class, id);
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.dao.Dao#update(com.csc.azubiblog.model.ValueObject)
	 */
	@Override
	public ValueObject update(ValueObject value) {
		Comment comment = m_entityManager.merge((Comment)value);
		m_entityManager.persist(comment);
		return comment;
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.dao.Dao#insert(com.csc.azubiblog.model.ValueObject)
	 */
	@Override
	public ValueObject insert(ValueObject value) {
		m_entityManager.persist((Comment)value);
		return (Comment)value;
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.dao.Dao#delete(com.csc.azubiblog.model.ValueObject)
	 */
	@Override
	public boolean delete(ValueObject value) {
		Comment comment = m_entityManager.merge((Comment)value);
		m_entityManager.remove(comment);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.dao.CommentDao#getCommentsByBlog(com.csc.azubiblog.model.Blog)
	 */
	@Override
	public List<Comment> getCommentsByBlog(Blog blog){
		TypedQuery<Comment> query = m_entityManager.createNamedQuery(Comment.FIND_COMMENT_BY_BLOG, Comment.class);
		query.setParameter("blogid", blog);
		
		return query.getResultList();
	}
}
