package com.csc.azubiblog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author NFriese, FKohlhaas
 * 
 * * Model class blog
 * Collects contents for "Blog", referencing on:
 * {@link Category} as category, {@link User} as author, {@link Comment} as
 * comment list
 */
@Entity
@Table(name = "Blogs")
@NamedQueries({
		@NamedQuery(name=Blog.GET_BLOGS_BY_AUTHOR, query="FROM Blog WHERE author = :author"), 
		@NamedQuery(name=Blog.GET_BLOGS_BY_CATEGORY, query="FROM Blog WHERE category = :category")
})
public class Blog extends ValueObject {

	/**
	 * Auto Generated serial versionUID
	 */
	private static final long serialVersionUID = 1136041743179144586L;
	public static final String GET_BLOGS_BY_AUTHOR = "get_blogs_by_author";
	public static final String GET_BLOGS_BY_CATEGORY = "get_blogs_by_category";
	
	/**
	 * m_title = Blog title
	 * m_category = Blog category
	 * m_commentsList = List of comments for the blog
	 * m_content = Blog content
	 */
	private String m_title;
	private Category m_category;
	private List<Comment> m_commentsList = new ArrayList<Comment>();
	private String m_content = "";
	
	public Blog(){}
	
	/**
	 * Constrcutor
	 * 
	 * @param title - Blog title
	 * @param category - Blog category
	 * @param author - Author Id
	 */
	public Blog(String title, Category category, Long author) {
		m_title = title;
		m_category = category;
		setAuthor(author);
	}

	/*
	 * Auto generated getters and setters using super(ValueObject)
	 */

	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BlogId")
	public long getId() {
		return super.getId();
	}

	@Override
	@Column(name = "CreateDate")
	public Date getCreationDate() {
		return super.getCreationDate();
	}

	@Override
	@Column(name = "UpdateDate")
	public Date getUpdateDate() {
		return super.getUpdateDate();
	}

	@Override
	@Column(name="Author")
	public Long getAuthor() {
		return super.getAuthor();
	}

	@Column(name = "Title")
	public String getTitle() {
		return m_title;
	}

	public void setTitle(String m_title) {
		this.m_title = m_title;
	}

	@Column(name = "Category")
	public Category getCategory() {
		return m_category;
	}

	public void setCategory(Category m_category) {
		this.m_category = m_category;
	}

	@OneToMany(targetEntity=Comment.class, mappedBy="blog", fetch=FetchType.EAGER)
	public List<Comment> getCommentsList() {
		return m_commentsList;
	}

	public void addComment(Comment comment) {
		this.m_commentsList.add(comment);
	}

	public void setCommentsList(List<Comment> m_commentsList) {
		this.m_commentsList = m_commentsList;
	}

	@Column(name = "Content")
	public String getContent() {
		return m_content;
	}

	public void setContent(String m_content) {
		this.m_content = m_content;
	}

}
