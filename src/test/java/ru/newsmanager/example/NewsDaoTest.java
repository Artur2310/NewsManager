package ru.newsmanager.example;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ru.newsmanager.example.model.News;
import ru.newsmanager.example.service.NewsService;

public class NewsDaoTest {


    @Mock
    private NewsService newsService;
    
    @Before
    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);
    }
    
    
	@Test
	public void testAddNews() {
		
		News news = new News("Title");
		News newsWithoutTitle = new News();
		
        when(newsService.addNews(news)).thenReturn(true);
        when(newsService.addNews(newsWithoutTitle)).thenReturn(false);

		assertThat(newsService.addNews(news),is(true));
		assertThat(newsService.addNews(newsWithoutTitle),is(false));

	}
	
	@Test 
	public void testDeleteNews(){
		News news = new News("Title");

		when(newsService.delete(1)).thenReturn(news);
		assertThat(newsService.delete(1),is(news));
		
	}
	
	
	@Test
	public void testGetNews(){
		News news = new News("Title");
		
		newsService.addNews(news);
		when(newsService.get(1)).thenReturn(news);
		assertThat(newsService.get(1),is(news));
	}

}
