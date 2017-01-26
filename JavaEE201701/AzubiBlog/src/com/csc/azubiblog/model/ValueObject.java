package com.csc.azubiblog.model;

import java.io.Serializable;
import java.util.Date;

public class ValueObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private Long m_author;
	private Date m_creationDate = new Date();
	private Date m_updateDate;

	public ValueObject() {
	}

//	public ValueObject(User author) {
//		if (author != null) {
//			m_author = Long.valueOf(author.getId());
//		}
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getAuthor() {
		return m_author;
	}

	public void setAuthor(Long author) {
		this.m_author = author;
	}

	public Date getCreationDate() {
		return m_creationDate;
	}

	public void setCreationDate(Date m_creationDate) {
		this.m_creationDate = m_creationDate;
	}

	public Date getUpdateDate() {
		return m_updateDate;
	}

	public void setUpdateDate(Date m_updateDate) {
		this.m_updateDate = m_updateDate;
	}

}
