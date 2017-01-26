package com.csc.azubiblog.controller.dao;

import com.csc.azubiblog.model.ValueObject;

public interface Dao {

	public ValueObject find(long id);
	
	public ValueObject update(ValueObject value);
	
	public ValueObject insert(ValueObject value);
	
	public boolean delete(ValueObject value);
	
}
