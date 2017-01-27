package com.csc.azubiblog.controller.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.csc.azubiblog.model.User;
import com.csc.azubiblog.model.ValueObject;

/**
 * @author NFriese, FKohlhaas
 * 
 *         Implementation of user data access using the UserDao interface
 */
@Stateless
public class UserDaoImpl implements UserDao {

	/**
	 * Calling a entityManager object from EntityManager
	 */
	@PersistenceContext(unitName = "ExampleDS")
	private EntityManager m_entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csc.azubiblog.controller.dao.Dao#find(long)
	 */
	@Override
	public ValueObject find(long id) {
		return m_entityManager.find(User.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csc.azubiblog.controller.dao.Dao#update(com.csc.azubiblog.model.
	 * ValueObject)
	 */
	@Override
	public ValueObject update(ValueObject value) {
		User user = m_entityManager.merge((User) value);
		m_entityManager.persist(user);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csc.azubiblog.controller.dao.Dao#insert(com.csc.azubiblog.model.
	 * ValueObject)
	 */
	@Override
	public ValueObject insert(ValueObject value) {
		m_entityManager.persist((User) value);
		return (User) value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csc.azubiblog.controller.dao.Dao#delete(com.csc.azubiblog.model.
	 * ValueObject)
	 */
	@Override
	public boolean delete(ValueObject value) {
		User user = m_entityManager.merge((User) value);
		m_entityManager.remove(user);
		return true;
	}

	@Override
	public List<User> getAllUser() {
		TypedQuery<User> query = m_entityManager.createNamedQuery(User.QUERY_ALL_USER, User.class);
		return query.getResultList();
	}

}
