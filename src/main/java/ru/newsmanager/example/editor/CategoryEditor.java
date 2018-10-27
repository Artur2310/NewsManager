package ru.newsmanager.example.editor;

import java.beans.PropertyEditorSupport;

import ru.newsmanager.example.model.Category;
import ru.newsmanager.example.service.CategoryService;

public class CategoryEditor extends PropertyEditorSupport{
	
	private CategoryService categoryService;

    public CategoryEditor(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override  
    public void setAsText(String text) throws IllegalArgumentException {
    
    	Category category = categoryService.get(Integer.valueOf(text));
        	
            this.setValue(category);
        
    }

    @Override
    public String getAsText() {
    	Category parent = new Category();
        if (this.getValue() != null) {
            parent = (Category) this.getValue();
        }
        return "";
    } 

}
