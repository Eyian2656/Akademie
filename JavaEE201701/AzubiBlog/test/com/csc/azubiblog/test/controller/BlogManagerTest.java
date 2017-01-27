package com.csc.azubiblog.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.csc.azubiblog.controller.BlogManager;
import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Category;

public class BlogManagerTest {

	private BlogManager m_blogManager;
	private InitialContext ctx;

	@BeforeClass
	public static void beforeClass() {
		Properties props = new Properties();

		try {
			props.load(BlogManagerTest.class.getClassLoader().getResourceAsStream("jndi.properties"));
		} catch (IOException e) {
			fail(e.getMessage());
		}

	}

	private boolean connect() {
		Properties props = new Properties();

		try {
			props.load(BlogManagerTest.class.getClassLoader().getResourceAsStream("jndi.properties"));
		} catch (IOException e) {
			fail(e.getMessage());
		}
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

	private boolean connetionClose() {
		try {
			ctx.close();
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
			fail(e.getMessage());
			return false;
		}
	}

	public List<Blog> getBlogsByAuthorTest(long id) {

		List<Blog> blogs = new ArrayList<Blog>();

		for (int i = 0; i <= 10; i++) {
			Blog testBlog;
			if (i % 2 == 0) {
				testBlog = new Blog("AwesomeBlog: How to be cool Part:" + i, Category.HAXX0R, Long.valueOf(id));
			} else {
				testBlog = new Blog("AwesomeBlog: How to be cool Part:" + i, Category.HAXX0R, Long.valueOf(id));
			}
			connect();
			blogs.add(m_blogManager.insertBlog(testBlog));
			connetionClose();
		}

		connect();
		List<Blog> resultBlogs = m_blogManager.getBlogsByAuthor(Long.valueOf(id));
		connetionClose();

		return resultBlogs;
	}

	@Test
	public void crudBlogTest() {
		Blog testBlog = new Blog("AwesomeBlog: How to be cool", Category.HAXX0R, null);

		connect();
		Blog resultBlog = m_blogManager.insertBlog(testBlog);
		connetionClose();
		assertNotNull(resultBlog);
		assertEquals("Title", testBlog.getTitle(), resultBlog.getTitle());
		assertEquals("Category", testBlog.getCategory(), resultBlog.getCategory());

		connect();
		Blog resultGetBlog = m_blogManager.getBlog(resultBlog.getId());
		connetionClose();
		assertNotNull(resultGetBlog);
		assertEquals("Title", testBlog.getTitle(), resultGetBlog.getTitle());
		assertEquals("Category", testBlog.getCategory(), resultGetBlog.getCategory());

		connect();
		String content = "Don´t be unattractive. Insert some text... wuhhh magic.";
		resultGetBlog.setContent(content);
		Blog resultUpdateBlog = m_blogManager.updateBlog(resultGetBlog);
		connetionClose();
		assertNotNull(resultGetBlog);
		assertEquals("ID", resultGetBlog.getId(), resultUpdateBlog.getId());
		assertEquals("Content", resultGetBlog.getContent(), resultUpdateBlog.getContent());

		connect();
		boolean isDeleted = m_blogManager.deleteBlog(resultUpdateBlog);
		connetionClose();
		assertTrue(isDeleted);

	}

	@Test
	public void getBlogsByAuthorTest() {

		List<Blog> blogs = new ArrayList<Blog>();

		for (int i = 0; i <= 10; i++) {
			Blog testBlog;
			if (i % 2 == 0) {
				testBlog = new Blog("AwesomeBlog: How to be cool Part:" + i, Category.HAXX0R, Long.valueOf(15));
			} else {
				testBlog = new Blog("AwesomeBlog: How to be cool Part:" + i, Category.HAXX0R, Long.valueOf(9));
			}
			connect();
			blogs.add(m_blogManager.insertBlog(testBlog));
			connetionClose();
		}

		connect();
		List<Blog> resultBlogs = m_blogManager.getBlogsByAuthor(Long.valueOf(15));
		connetionClose();
		assertNotNull(resultBlogs);
		assertTrue(resultBlogs.size() >= 1);

		for (Blog compareBlog : blogs) {
			for (Blog resultBlog : resultBlogs) {
				if (resultBlog.equals(compareBlog)) {
					assertEquals("Id", compareBlog.getId(), resultBlog.getId());
					assertEquals("User", Long.valueOf(15), resultBlog.getAuthor());
				}
			}
		}

		for (Blog compareBlog : blogs) {
			connect();
			assertTrue(m_blogManager.deleteBlog(compareBlog));
			connetionClose();
		}

	}

	@Test
	public void getBlogsByCategoryTest() {
		List<Blog> blogs = new ArrayList<Blog>();

		for (int i = 0; i <= 10; i++) {
			Blog testBlog;
			if (i % 2 == 0) {
				testBlog = new Blog("AwesomeBlog: How to be cool Part:" + i, Category.HOT, null);
			} else {
				testBlog = new Blog("AwesomeBlog: How to be cool Part:" + i, Category.HAXX0R, null);
			}
			connect();
			blogs.add(m_blogManager.insertBlog(testBlog));
			connetionClose();
		}

		connect();
		List<Blog> resultBlogs = m_blogManager.getBlogsByCategory(Category.HAXX0R);
		connetionClose();
		assertNotNull(resultBlogs);
		assertTrue(resultBlogs.size() >= 1);

		for (Blog compareBlog : blogs) {
			for (Blog resultBlog : resultBlogs) {
				if (resultBlog.equals(compareBlog)) {
					assertEquals("Id", compareBlog.getId(), resultBlog.getId());
					assertEquals("Category", Category.HAXX0R, resultBlog.getCategory());
				}
			}
		}

		for (Blog compareBlog : blogs) {
			connect();
			assertTrue(m_blogManager.deleteBlog(compareBlog));
			connetionClose();
		}
	}
}
