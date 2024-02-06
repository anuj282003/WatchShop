package com.watchshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watchshop.entities.Category;
import com.watchshop.repositories.CategoryRepository;

@Service
public class CategoryServiceImplementation implements CategoryService{
			
	@Autowired
	CategoryRepository  categoryrepository;

	@Override
	public void addCategory(Category category) {
		    categoryrepository.save(category);
		
	}

	@Override
	public List<Category> getAllCategory() {
		
		return categoryrepository.findAll();
	}

	@Override
	public void removeCategoryById(int id) {
		categoryrepository.deleteById(id);
	}

	@Override
	public Optional<Category> getCategoryById(int id) {
		Optional<Category> cat =categoryrepository.findById(id);
		System.out.println(cat.get());
		return categoryrepository.findById(id);
	}

	

	
	
	
	
	
	
	
	
	
}
