package ru.newsmanager.example.dao;

import java.util.List;

import ru.newsmanager.example.model.News;

public interface NewsDao {

	public List<News> getNewsByTitle(String title);

	public List<News> getNewsByContent(String str);

}
