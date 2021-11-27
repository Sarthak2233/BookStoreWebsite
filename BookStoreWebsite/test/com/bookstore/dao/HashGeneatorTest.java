package com.bookstore.dao;
import static org.junit.Assert.*;
import org.junit.Test;
public class HashGeneatorTest {

	public HashGeneatorTest() {
		// TODO Auto-generated constructor stub
	}
	@Test
	public void testGeneratorMD5() {
		String password="Brucew@yne123";
		String encryptPassword=HashGenerator.generateMD5(password);
		
		System.out.println(encryptPassword);
		assertTrue(true);
		
	}

}
