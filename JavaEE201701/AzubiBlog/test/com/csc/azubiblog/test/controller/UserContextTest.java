package com.csc.azubiblog.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.csc.azubiblog.controller.Usercontext;
import com.csc.azubiblog.model.User;

public class UserContextTest {

	private Usercontext m_userContext;
	private static Properties props;
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
	
	private boolean connect(){
		try {
			ctx = new InitialContext(props);

			m_userContext = (Usercontext) ctx
					.lookup("/AzubiBlog/UsercontextEJB!com.csc.azubiblog.controller.Usercontext");
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
	public void crudUsercontextTest(){
		User testUser = new User("Tester", "Testah", new Date(0), "xX_AwesaomeTester_Haxx0r_Xx", "Pa$$w0rd");
		
		connect();
		User resultUser = m_userContext.insertUser(testUser);
		connetionClose();
		assertNotNull(resultUser);
		assertEquals("Name", testUser.getLastname(), resultUser.getLastname());
		
		connect();
		User resultGetUser = m_userContext.getUser(resultUser.getId());
		connetionClose();
		assertNotNull(resultUser);
		assertEquals("Name", resultUser.getLastname(), resultGetUser.getLastname());
		assertEquals("ID", resultUser.getId(), resultGetUser.getId());
		
		resultUser = resultGetUser;
		resultUser.setBirthday(new Date());
		connect();
		User resultUpdateUser = m_userContext.updateUser(resultUser);
		connetionClose();
		assertNotNull(resultUpdateUser);
		assertEquals("Name", resultUser.getLastname(), resultUpdateUser.getLastname());
		assertEquals("ID", resultUser.getId(), resultUpdateUser.getId());
		assertFalse(new Date(0).equals(resultUpdateUser.getBirthday()));
		
		connect();
		boolean delete = m_userContext.deleteUser(resultUpdateUser);
		connetionClose();
		assertTrue(delete);
	}
	
	@Test
	public void getAllUserTest(){
		List<User> compareList = new ArrayList<User>();
		for(int i = 0; i <= 5; i++){
			User testUser = new User("Tester"+i, "Testah"+i, new Date(0), "xX_AwesaomeTester_Haxx0r_Xx"+i, "Pa$$w0rd");
			connect();
			testUser = m_userContext.insertUser(testUser);
			connetionClose();
			compareList.add(testUser);
		}
		
		connect();
		List<User> resultList = m_userContext.getAllUser();
		connetionClose();
		
		assertNotNull(resultList);
		assertTrue(resultList.size()>=1);
		
		for(User compareUser : compareList){
			for(User resultUser: resultList){
				if(resultUser.equals(compareUser)){
					assertEquals(resultUser.getId(), compareUser.getId());
				}
			}
			connect();
			assertTrue(m_userContext.deleteUser(compareUser));
			connetionClose();
		}
		
		
	}
	
}
