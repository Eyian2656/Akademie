package com.csc.azubiblog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="User")
@NamedQueries(
		@NamedQuery(name=User.QUERY_ALL_USER, query="FROM User")
		)
public class User extends ValueObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7858969274682209392L;

	public static final String QUERY_ALL_USER = "query_all_user";

	private String m_lastname;
	private String m_surname;
	private Date m_birthday;
	private String m_alias;
	private boolean m_online;
	private Date m_lastOnline;
	private String m_password;
	
	public User(){}
	
	public User(String lastname, String surname, Date birthday, String alias, String password){
		
		this.m_lastname = lastname;
		this.m_surname = surname;
		this.m_birthday = birthday;
		this.m_alias = alias;
		this.m_password = password;
	}

	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="UserId")
	public long getId(){
		return super.getId();
	}
	
	@Override
	@Column(name="CreateDate")
	public Date getCreationDate(){
		return super.getCreationDate();
	}
	
	@Override
	@Column(name="UpdateDate")
	public Date getUpdateDate(){
		return super.getUpdateDate();
	}
	
	@Override
	@Column(name="Author")
	public Long getAuthor(){
		return super.getAuthor();
	}
	
	@Column(name="Lastname")
	public String getLastname() {
		return m_lastname;
	}

	public void setLastname(String m_lastname) {
		this.m_lastname = m_lastname;
	}

	@Column(name="Surname")
	public String getSurnamen() {
		return m_surname;
	}

	public void setSurnamen(String m_surnamen) {
		this.m_surname = m_surnamen;
	}

	@Column(name="Birthday")
	public Date getBirthday() {
		return m_birthday;
	}

	public void setBirthday(Date m_birthday) {
		this.m_birthday = m_birthday;
	}

	@Column(name="Alias")
	public String getAlias() {
		return m_alias;
	}

	public void setAlias(String m_alias) {
		this.m_alias = m_alias;
	}

	@Transient
	public boolean isOnline() {
		return m_online;
	}

	public void setOnline(boolean m_online) {
		this.m_online = m_online;
	}

	@Column(name="LastOnline")
	public Date getLastOnline() {
		return m_lastOnline;
	}

	public void setLastOnline(Date m_lastOnline) {
		this.m_lastOnline = m_lastOnline;
	}

	@Column(name="Password")
	public String getPassword() {
		return m_password;
	}

	public void setPassword(String m_password) {
		this.m_password = m_password;
	}
	
}
