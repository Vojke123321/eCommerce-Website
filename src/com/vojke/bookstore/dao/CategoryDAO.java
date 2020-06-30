package com.vojke.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.vojke.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {
	
	public CategoryDAO() {
	}

	@Override
	public Category create(Category category) {
		// TODO Auto-generated method stub
		return super.create(category);
	}
	@Override
	public Category update(Category entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Category get(Object id) {
		return super.find(Category.class, id);
	}

	@Override
	public void delete(Object id) { 
		super.delete(Category.class, id);
	}

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Category.countAll");
	}

	public Category findByName(String categoryName) {
		List<Category> result=super.findWithNamedQueryAndParamater("category.findByName","name",categoryName);
		 if(result!=null && result.size()>0) {
			 return result.get(0);
		 }
		return null;
	}

	

	

}
