package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest {
	private static CategoryDAO categoryDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDao=new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDao.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat= new Category("Java");
		Category category=categoryDao.create(newCat);
		
		assertTrue(category !=null  &&  category.getCategoryId()>0 );
	}

	@Test
	public void testUpdateCategory() {
		Category c= new Category();
		c.setCategoryId(12);
		c.setName("Python");
		c=categoryDao.update(c);
		
		assertEquals("Python", c.getName());
		
	}

	@Test
	public void testGet() {
		Integer catId=12;
		Category c=categoryDao.get(catId);
		
		assertNotNull(c);
	}

	@Test
	public void testDeleteCategory() {
		Integer catId=13;
		categoryDao.delete(catId);
		Category c=categoryDao.get(catId);
		
		assertNull(c);
		
	}

	@Test
	public void testListAll() {
		List<Category> listCategory=categoryDao.listAll();
		for(Category category:listCategory) {
			System.out.println(category.getName());
		}
		assertTrue(listCategory.size()>0);
	}

	@Test
	public void testCount() {		
		long totalCategory=categoryDao.count();
		System.out.println("The counted number of books are "+ totalCategory );
		assertEquals(1,totalCategory);
	}
	
	@Test
	public void testFindByName() {
		String name="Python";
		Category category=categoryDao.findByName(name);
		
		assertNotNull(category);
	}

}
