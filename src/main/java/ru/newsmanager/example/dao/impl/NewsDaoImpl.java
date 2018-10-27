package ru.newsmanager.example.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ru.newsmanager.example.dao.NewsDao;
import ru.newsmanager.example.model.News;
import ru.newsmanager.example.model.News_;


@Repository
public class NewsDaoImpl extends AbstractDaoImpl<News, Integer> implements NewsDao {

	public NewsDaoImpl(){
		Class type = new News().getClass();
		this.setType(type);
	}
	
	public List<News> getNewsByTitle(String title){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<News> newsLikeCriteria = entityManager.getCriteriaBuilder().createQuery(News.class);
		Root<News> likeNewsRoot = newsLikeCriteria.from(News.class);
		newsLikeCriteria.select(likeNewsRoot);
		newsLikeCriteria.where(cb.like(likeNewsRoot.get(News_.title), "%"+title + "%"));

		newsLikeCriteria.orderBy(cb.asc(likeNewsRoot.get("id")));

		return entityManager.createQuery(newsLikeCriteria).getResultList();
	}
	 
	public List<News> getNewsByContent(String content){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<News> newsLikeCriteria = entityManager.getCriteriaBuilder().createQuery(News.class);
		Root<News> likeNewsRoot = newsLikeCriteria.from(News.class);
		newsLikeCriteria.select(likeNewsRoot);
		newsLikeCriteria.where(cb.like(likeNewsRoot.get(News_.content), "%"+content + "%"));

		newsLikeCriteria.orderBy(cb.asc(likeNewsRoot.get("id")));

		return entityManager.createQuery(newsLikeCriteria).getResultList();
	}
	
}
