package com.csc.azubiblog.test.controller;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.csc.azubiblog.controller.BlogManager;
import com.csc.azubiblog.controller.CommentManager;
import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Category;
import com.csc.azubiblog.model.Comment;

public class CommentManagerTest {

	private BlogManager m_blogManager;
	private CommentManager m_commentManager;
	private static Properties props;
	private InitialContext ctx;
	
	private Blog blog;

	@BeforeClass
	public static void beforeClass() {
		Properties props = new Properties();
		
			try {
				props.load(BlogManagerTest.class.getClassLoader().getResourceAsStream("jndi.properties"));
			} catch (IOException e) {
				fail(e.getMessage());
			}
	}
	
	private boolean connectBlogManager(){
		try {
			ctx = new InitialContext(props);

			m_blogManager = (BlogManager) ctx
					.lookup("/AzubiBlog/BlogManagerEJB!com.csc.azubiblog.controller.BlogManager");
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return false;
		}
	}
	
	private boolean connectCommentManager(){
		try {
			ctx = new InitialContext(props);

			m_commentManager = (CommentManager) ctx
					.lookup("/AzubiBlog/CommentManagerEJB!com.csc.azubiblog.controller.CommentManager");
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return false;
		}
	}
	
	private boolean connetionClose(){
		try {
			ctx.close();
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return false;
		}
	}
	
	@Test
	public void allCommentTest(){
		blog = new Blog("MyTestBlog", Category.HOT, Long.valueOf(0));
		connectBlogManager();
		blog = m_blogManager.insertBlog(blog);
		connetionClose();
		
		Comment testComment = new Comment(Long.valueOf(0), "Awesome Comment... yeay!");
		testComment.setBlog(blog);
		
		connectCommentManager();
		Comment resultComment = m_commentManager.insertComment(testComment, blog.getId());
		connetionClose();
		
		assertNotNull(resultComment);
		assertEquals("Content", testComment.getContent() , resultComment.getContent());		
		
		connectCommentManager();
		List<Comment> resultList = m_commentManager.getCommentsByBlog(blog);
		connetionClose();
		
		assertNotNull(resultList);
		assertTrue(resultList.size() >=1);
	}
	
}
