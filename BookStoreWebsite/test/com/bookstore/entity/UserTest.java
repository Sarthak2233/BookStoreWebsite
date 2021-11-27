package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class UserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Users u1=new Users();
		u1.setEmail("sarthak.basnet5@gmail.com");
		u1.setFullName("SarthakBasnet");
		u1.setPassword("batman12345");
		
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(u1);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("Presisted");
		
		
	}

}
