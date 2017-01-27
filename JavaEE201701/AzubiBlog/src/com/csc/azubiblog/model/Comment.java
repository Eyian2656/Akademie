package com.csc.azubiblog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author NFriese, FKohlhaas
 * 
 * * Model class comment
 * Collects contents for "comment", referencing on:
 * {@link Blog} as blog, {@link User} as author
 */
@Entity
@Table(name = "Comments")
@NamedQueries(
		@NamedQuery(name=Comment.FIND_COMMENT_BY_BLOG, query="FROM Comment WHERE blog = :blogid"))
public class Comment extends ValueObject {
	
	/**
	 * Auto generated serial versionUID
	 */
	private static final long serialVersionUID = 1172966368902738644L;

	public static final String FIND_COMMENT_BY_BLOG = "find_comment_by_blog";

	private String content;
	private Blog blog;

	/**
	 * Empty constructor
	 */
	public Comment(){}
	
	/**
	 * Constructor
	 * @param author - Comment author
	 * @param content - Comment content
	 */
	public Comment(Long author, String content){
		setAuthor(author);
		this.content = content;
	}
	
	/*
	 * Auto generated getters and setters using super(ValueObject)
	 */
	
	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CommentId")
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

	@Column(name="Content")
	public String getContent() {
		return content;
	}

	public void setContent(String m_content) {
		this.content = m_content;
	}
	
	@ManyToOne
	@JoinColumn(name="fk_blog")
	public Blog getBlog(){
		return blog;
	}
	
	public void setBlog(Blog value){
		blog = value;
	}
	
}
