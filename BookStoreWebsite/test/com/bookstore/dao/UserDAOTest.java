package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	private static UserDAO userDao;
	
	@BeforeClass
	public static void setUpClass() throws Exception{
    userDao=new UserDAO();
	}

	@Test
	public void testCreateUsers() {
		//fail("Not yet implemented");
		Users u1=new Users();
		u1.setEmail("sambodhi@gmail.com");
		u1.setFullName("SambodhiBasnet");
		u1.setPassword("kukurni123");
		
		UserDAO userDao=new UserDAO();
		u1=userDao.create(u1);
		
		assertTrue(u1.getUserId()>0);
	}
	
		@Test(expected = PersistenceException.class)
		public void testCreateUsersFieldsNotSet() {
			Users u1=new Users();
			u1=userDao.create(u1);
	
		}
		@Test	
		public void updateUsers() {
			Users u= new Users();
			u=userDao.get(67);
			u.setEmail("bhairav@gmail.com");
			u.setFullName("Bhairavsivaa");
			u= userDao.update(u);
			String expected="Bhairavsivaa";
			String actual=u.getFullName();
			assertEquals(expected,actual);
			
			
		}
		@Test
		public void testGetUsers() {
			Integer userId=25;
			Users user=userDao.get(userId);
			assertNotNull(user);
		}
		@Test
		public void testGetUserNotFound() {
			Integer userId=1;
			Users user=userDao.get(userId);
			assertNull(user);
		}
		
		@Test
		public void testDeleteUser() {
		Integer userId=25;
		userDao.delete(userId);
		Users user= userDao.get(userId);
		assertNull(user);
		}
		@Test(expected=Exception.class)
		public void testDeleteNonExistUsers() {
		Integer userId=55;
		userDao.delete(userId);
		}
		
		@Test
		public void testListAll() {
			List<Users> listUsers=userDao.listAll();
			for(Users user: listUsers) {
				System.out.println(user.getEmail());
			}
			assertTrue(listUsers.size()>0);
		}
		@Test
		public void testCount() {
			long totalUsers=userDao.count();
			assertEquals(3, totalUsers);
		}
		
		@Test
		public void testFindByEmail() {
			String email="haribadhur@hotmail,com";
			Users  user=userDao.findByEmail(email);
			
			assertNotNull(user);
		}
		
		@Test
		public void testcheckLoginSuccess() {
			String email="sarthak.basnet5@gmail.com";
			String password="Brucew@yne123";
			boolean actual=userDao.checkLogin(email, password);
			assertTrue(actual);
		}
		
		@Test
		public void testcheckLoginFaileds() {
			String email="sarthak.basnet@gmail.com";
			String password="Brucew@yne123";
			boolean actual=userDao.checkLogin(email, password);
			assertFalse(actual);
		}
		
		@AfterClass
		public static void tearDownClass() throws Exception{
		userDao.close();
		}
	

}

