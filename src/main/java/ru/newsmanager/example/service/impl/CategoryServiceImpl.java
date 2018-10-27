package ru.newsmanager.example.service.impl;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.newsmanager.example.dao.impl.CategoryDaoImpl;
import ru.newsmanager.example.model.Category;
import ru.newsmanager.example.model.News;
import ru.newsmanager.example.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	private boolean init = false;
	
	@Autowired
	CategoryDaoImpl categoryDao;
	
	@Override
	@Transactional
	public boolean addCategory(Category category) {

		if (category == null) {
			logger.info("News not added");
			return false;

		}

		categoryDao.add(category);
		logger.info("News '" + category.getTitle() + "' added");
		return true;

	}
	
	@Override
	@Transactional
	public List<Category> list() {
		return categoryDao.list();
	}

	@Override
	@Transactional
	public Long count() {
		return categoryDao.count();
	}
	@Override
	@Transactional
	public Category get(Integer id) {

		return categoryDao.get(id);

	}
	
	@Override
	@Transactional
	public List<News> getListNewsByCategory(Integer id){
		List<News> newsList = new ArrayList<News>(get(id).getNews());
		Collections.sort(newsList, (a,b)-> a.compareByData(b));
		
		return newsList;
	}


	@Override
	public boolean isInit(){
		return init;
	}
	
	@Override
	public void setInit(boolean value){
		init = value;
	}
	
	@Override
	@Transactional
	public void initDatabase(){
		init = false;

		if(count() != 0) {
			return; 
		}
	    List<Category> categories = new ArrayList<>();
	    categories.add(new Category("Бизнес"));
	    categories.add(new Category("Политика"));
	    categories.add(new Category("Технологии"));
	    categories.add(new Category("Спорт"));
	    categories.add(new Category("Кино"));

		categories.forEach(item -> addCategory(item));
			}
}
