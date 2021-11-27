package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO() {
	
	}

	@Override
	public Category create(Category category	) {
		return super.create(category);
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return super.update(category);
	}

	@Override
	public Category get(Object id) {
		// TODO Auto-generated method stub
		return super.find(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Category.class, id);
		
	}

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Category.countAll");
	}
	public Category findByName(String name ) {
	    List<Category> listCategory=super.findWithNamedQuery("Category.findByName", "name", name);
	 	if (listCategory!= null && listCategory.size()>0) {
		
	 		return listCategory.get(0);
	 	}
		return null;
		
		
	}
}
