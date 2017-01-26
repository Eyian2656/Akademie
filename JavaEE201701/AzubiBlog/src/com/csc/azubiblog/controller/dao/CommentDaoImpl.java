package com.csc.azubiblog.controller.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Comment;
import com.csc.azubiblog.model.ValueObject;

@Stateless
public class CommentDaoImpl implements CommentDao{

	@PersistenceContext(unitName="ExampleDS")
	private EntityManager m_entityManager;	
	
	@Override
	public ValueObject find(long id) {
		return m_entityManager.find(Comment.class, id);
	}

	@Override
	public ValueObject update(ValueObject value) {
		Comment comment = m_entityManager.merge((Comment)value);
		m_entityManager.persist(comment);
		return comment;
	}

	@Override
	public ValueObject insert(ValueObject value) {
		m_entityManager.persist((Comment)value);
		return (Comment)value;
	}

	@Override
	public boolean delete(ValueObject value) {
		Comment comment = m_entityManager.merge((Comment)value);
		m_entityManager.remove(comment);
		return true;
	}

	@Override
	public List<Comment> getCommentsByBlog(Blog blog){
		TypedQuery<Comment> query = m_entityManager.createNamedQuery(Comment.FIND_COMMENT_BY_BLOG, Comment.class);
		query.setParameter("blogid", blog);
		
		return query.getResultList();
	}
}
