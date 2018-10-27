package ru.newsmanager.example.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(News.class)
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public class News_ {

	public static volatile SingularAttribute<News, Integer> id;
	public static volatile SingularAttribute<News, String> title;
	public static volatile SingularAttribute<News, String> content;
	public static volatile SingularAttribute<News, Date> date;
	public static volatile SingularAttribute<News, Category> category;


}
