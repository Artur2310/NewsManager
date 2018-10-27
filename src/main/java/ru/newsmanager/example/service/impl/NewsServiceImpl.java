package ru.newsmanager.example.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.newsmanager.example.dao.impl.NewsDaoImpl;
import ru.newsmanager.example.model.News;
import ru.newsmanager.example.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Autowired
	NewsDaoImpl newsDao;

	@Override
	@Transactional
	public boolean addNews(News news) {

		if (news == null) {
			logger.info("News not added");
			return false;

		}

		news.setDate(new Date());
		newsDao.add(news);
		logger.info("News '" + news.getTitle() + "' added");
		return true;

	}

	@Override
	@Transactional
	public News delete(Integer id) {

		return newsDao.delete(id);
	}

	@Override
	@Transactional
	public void update(News news) {
		
		newsDao.update(news);
		
	}

	@Override
	@Transactional
	public News get(Integer id) {

		return newsDao.get(id);

	}

	@Override
	@Transactional
	public List<News> list() {
		return newsDao.list();
	}

	@Override
	@Transactional
	public Long count() {
		return newsDao.count();
	}
	
	//Search by title and content
	@Override
	@Transactional
	public List<News> getNewsByString(String str){
		List<News> listByTitle = newsDao.getNewsByTitle(str);
		List<News> listByContent = newsDao.getNewsByContent(str);
		
		//Exclude repetitions
		Set<News> newsSet = new HashSet<News>(listByTitle);
		newsSet.addAll(listByContent);
		
		List<News> listOutput = new ArrayList<News>(newsSet);
		Collections.sort(listOutput, (a,b)-> b.compareByData(a));
		return listOutput;
	}


}
