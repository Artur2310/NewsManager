package ru.newsmanager.example.service;

import java.util.List;

import ru.newsmanager.example.model.Category;
import ru.newsmanager.example.model.News;

public interface CategoryService {

	public boolean addCategory(Category category);

	public List<Category> list();

	public Long count();
	
	public Category get(Integer id);
	
	public List<News> getListNewsByCategory(Integer id);
	
	public boolean isInit();
	
	public void setInit(boolean value);
	
	public void initDatabase();
	
}
