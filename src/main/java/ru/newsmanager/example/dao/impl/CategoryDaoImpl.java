package ru.newsmanager.example.dao.impl;

import org.springframework.stereotype.Repository;

import ru.newsmanager.example.dao.CategoryDao;
import ru.newsmanager.example.model.Category;

@Repository
public class CategoryDaoImpl extends AbstractDaoImpl<Category, Integer> implements CategoryDao{

	public CategoryDaoImpl(){
		//Specify type in abstract class
		Class type = new Category().getClass();
		this.setType(type);
	}
}
