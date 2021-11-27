package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest {
	private static BookDAO bookDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao= new BookDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDao.close(); //called from JPA main class
		}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook=new Book();
		Category category=new Category("Advance Java");
		category.setCategoryId(16);
		newBook.setCategory(category);
		
		newBook.setTitle("Java:The Complete Reference, Seventh Edition");
		newBook.setAuthor("Herbert Schildt");
		newBook.setDescription("This book is a comprehensive guide to the Java language, describing its syntax, keywords,\r\n"
				+ "and fundamental programming principles");
		newBook.setPrice(1200f);
		newBook.setIsbn("098-37-65419");
		
		DateFormat DateFormat= new SimpleDateFormat("mm/dd/yy");
		Date publishDate=DateFormat.parse("4/28/2021");
		newBook.setPublishDate(publishDate);
		
		String imagePath="C:/Users/Sarthak/Desktop/New Folder/9780070636774_9.jpeg";
		byte[] imageBytes=Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book createdBook=bookDao.create(newBook);		
		assertTrue(createdBook.getBookId() > 0);
		
	}
	@Test
	public void testUpdateBook() throws ParseException, IOException{
		Book existBook= new Book();
		existBook.setBookId(36);
		
		Category category= new Category("Python");
		category.setCategoryId(12);
		existBook.setCategory(category);
		
		existBook.setTitle("Python Projects for Beginners.");
		existBook.setAuthor("Cornor P6 Millikan");
		existBook.setDescription("A Ten-weeks Bootcamp Approach to Python Programming");
		existBook.setIsbn("000258251968");
		existBook.setPrice(2800);
		
		DateFormat DateFormat= new SimpleDateFormat("mm/dd/yy");
		Date publishDate=DateFormat.parse("4/29/2021");
		existBook.setPublishDate(publishDate);
		
		String imagePath="C:\\Users\\Sarthak\\Desktop\\New folder\\pythonphoto.jpg";
		byte[] image=Files.readAllBytes(Path.of(imagePath));
		existBook.setImage(image);
		
		Book updatedBook=bookDao.update(existBook);
		assertEquals(updatedBook.getTitle(),"Python Projects for Beginners.");
		
		
	}

	@Test(expected=EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId=100;
		bookDao.delete(bookId);
	}
	@Test
	public void testDeleteSuccess() {
		Integer bookId=37;
		bookDao.delete(bookId);
		
		assertTrue(true);
	}

	@Test
	public void testGetBook() {
		Integer bookId=32;
		Book book= bookDao.get(bookId);
		assertNotNull(book);
	}
	
	@Test
	public void testListAll() {
		List<Book> listBooks=bookDao.listAll();
		
		for(Book aBook:listBooks) {
			System.out.println(aBook.getTitle() +"  "+"  "+ aBook.getAuthor());
		}
		
		assertFalse(listBooks.isEmpty());
	}
	
	@Test
	public void testFindByTitleNotExist() {
		Book book=bookDao.findByTitle("ALL Hands on Java");
		assertNull(book);
	}
	
	@Test
	public void testFindByTitleExist() {
		Book book=bookDao.findByTitle("Python Projects for Beginners.");
		
		System.out.println(book.getTitle());
		System.out.println(book.getAuthor());
		assertNotNull(book);
	}
	
	@Test
	public void testListByCategory() {
	int categoryId=12;
		List<Book> listBook= bookDao.listByCategory(categoryId);
		assertTrue(listBook.size()>0);
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> listNewBook=bookDao.listNewBooks() ;
		for(Book ab: listNewBook) {
			System.out.println(ab.getTitle()+"-----"+ab.getPublishDate());
		}
		assertEquals(4,listNewBook.size());
	}
	
	@Test
	public void testSearhBookInTitle() {
		String keyword="The";
		List<Book> result=bookDao.Search(keyword);
		for(Book i: result) {
			System.out.println(i.getTitle());
		}
		assertEquals(3,result.size());
	}
	@Test
	public void testSearhBookInAuthor() {
		String keyword="Mark";
		List<Book> result=bookDao.Search(keyword);
		for(Book i: result) {
			System.out.println(i.getTitle());
		}
		assertEquals(1,result.size());
	}
	
	@Test
	public void testSearhBookInDescrotpion() {
		String keyword="engineering ";
		List<Book> result=bookDao.Search(keyword);
		for(Book i: result) {
			System.out.println(i.getTitle());
		}
		assertEquals(1,result.size());
	}
	@Test
	public void count() {
		long totalBooks=bookDao.count();
		assertEquals(2, totalBooks);
	}
	
	@Test 
	public void testCountByCategory() {
		int categoryId=16;
		long numofBooks=bookDao.countByCategory(categoryId);
		
		assertTrue(numofBooks==2);
	}
	
}

