package ru.newsmanager.example.service;

import java.util.List;

import ru.newsmanager.example.model.Category;
import ru.newsmanager.example.model.News;

public interface NewsService {

	    public boolean addNews(News news);

	    public News delete(Integer id);
	    
	    public void update(News news);
	    
	    public News get(Integer id);
	    
	    public List<News> list();
	        
	    public Long count();
	    
		public List<News> getNewsByString(String str);

	    
}
