package ru.newsmanager.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.newsmanager.example.editor.CategoryEditor;
import ru.newsmanager.example.model.Category;
import ru.newsmanager.example.model.News;
import ru.newsmanager.example.service.CategoryService;
import ru.newsmanager.example.service.NewsService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	NewsService newsService;

	@Autowired
	CategoryService categoryService;

	//Start page
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {

		if(!categoryService.isInit()){
			categoryService.initDatabase();
		}
		String category = request.getParameter("category");
		String search = request.getParameter("search");
		

		List<News> newsList;
		List<Category> categoryList = categoryService.list();
		model.addObject("categoryList", categoryList);

		model.setViewName("index");

		if (category != null && !category.isEmpty()) {
			newsList = categoryService.getListNewsByCategory(Integer.valueOf(category));

			model.addObject("newsList", newsList);
			return model;

		}
		if(search != null && !search.isEmpty()){
			newsList = newsService.getNewsByString(search);
			model.addObject("newsList", newsList);
			
			model.addObject("search", search);
			return model;
		}

		newsList = newsService.list();

		model.addObject("newsList", newsList);

		return model;
	}

	//Return a page for adding a news
	@RequestMapping(value = "/add_news", method = RequestMethod.GET)
	public ModelAndView addNewsPage(ModelAndView model, HttpServletRequest request) {

		News news = new News();

		List<Category> categoryList = categoryService.list();

		model.addObject("news", news);
		model.addObject("categoryList", categoryList);
		model.setViewName("add_news");

		return model;
	}

	// Adding a new news and send to the home page
	@RequestMapping(value = "/add_news", method = RequestMethod.POST)
	public ModelAndView addNews(@Valid News news, BindingResult result, ModelAndView model) {

		if (result.hasErrors()) {

			List<Category> categoryList = categoryService.list();

			model.addObject("news", new News());
			model.addObject("categoryList", categoryList);
			model.setViewName("add_news");
			return model;

		}
		newsService.addNews(news);

		model.setViewName("redirect:/");
		return model;

	}
	
	//Return a page for updating a news
	@RequestMapping(value = "/update_news", method = RequestMethod.GET)
	public ModelAndView upadteNewsPage(HttpServletRequest request, ModelAndView model) {

		String id = request.getParameter("id");
		System.out.println(id);
		News news = newsService.get(Integer.valueOf(id));

		if(news==null){
			model.setViewName("redirect:/");
			return model;
		}
		List<Category> categoryList = categoryService.list();

		model.addObject("news", news);
		model.addObject("categoryList", categoryList);
		model.setViewName("update_news");

		return model;
	}
	
		@RequestMapping(value = "/update_news", method = RequestMethod.POST)
		public ModelAndView updateNews(@Valid News news, BindingResult result, ModelAndView model) {

			if (result.hasErrors()) {

				List<Category> categoryList = categoryService.list();

				model.addObject("news", news);
				model.addObject("categoryList", categoryList);
				model.setViewName("update_news");
				return model;

			}
			newsService.update(news);

			model.setViewName("redirect:/");
			return model;

		}
		
		@RequestMapping(value = "/delete_news", method = RequestMethod.GET)
		public ModelAndView deleteNewsPage(HttpServletRequest request, ModelAndView model) {

			String id = request.getParameter("id");
			
			if(id != null && !id.isEmpty()){
				newsService.delete(Integer.valueOf(id));
			}
			
			model.setViewName("redirect:/");

			return model;
		}
		
		

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, orderDateEditor);	}
	
}
