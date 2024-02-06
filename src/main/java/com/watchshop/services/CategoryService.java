package com.watchshop.services;

import java.util.List;
import java.util.Optional;

import com.watchshop.entities.Category;

public interface CategoryService {
		public void addCategory(Category category) ;
		public  List<Category> getAllCategory();
		public void removeCategoryById(int id);
		 public Optional<Category> getCategoryById(int id);

//		public Optional<Category> getCategoryById(Category category);
		
	
		
}
