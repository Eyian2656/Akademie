package com.csc.azubiblog.controller.dao;

import com.csc.azubiblog.model.ValueObject;

/**
 * @author NFriese, FKohlhaas
 * 
 * Global super for data access-administration methods
 */
public interface Dao {

	/**
	 * @param id
	 * @return
	 */
	public ValueObject find(long id);
	
	/**
	 * @param value
	 * @return
	 */
	public ValueObject update(ValueObject value);
	
	/**
	 * @param value
	 * @return
	 */
	public ValueObject insert(ValueObject value);
	
	/**
	 * @param value
	 * @return
	 */
	public boolean delete(ValueObject value);
	
}
