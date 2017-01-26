package com.csc.azubiblog.controller.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.csc.azubiblog.model.User;
import com.csc.azubiblog.model.ValueObject;

@Stateless
public class UserDaoImpl implements UserDao{
	@PersistenceContext(unitName="ExampleDS")
	private EntityManager m_entityManager;	
	
	@Override
	public ValueObject find(long id) {
		return m_entityManager.find(User.class, id);
	}

	@Override
	public ValueObject update(ValueObject value) {
		User user = m_entityManager.merge((User)value);
		m_entityManager.persist(user);
		return user;
	}

	@Override
	public ValueObject insert(ValueObject value) {
		m_entityManager.persist((User)value);
		return (User)value;
	}

	@Override
	public boolean delete(ValueObject value) {
		User user = m_entityManager.merge((User)value);
		m_entityManager.remove(user);
		return true;
	}

	@Override
	public List<User> getAllUser(){
		TypedQuery<User> query = m_entityManager.createNamedQuery(User.QUERY_ALL_USER, User.class);		
		return query.getResultList();
	}
	

}
