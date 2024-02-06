package com.watchshop.services;

import java.util.List;
import java.util.Optional;

import com.watchshop.entities.Product;

public interface ProductService {
		public List<Product> getAllProduct(Integer pageNumber, Integer pageName);
		public void addProduct (Product product);
		public void removeProductById(long Id);
		public Optional<Product> getProductById(long id);
//		public List<Product> getAllProductByCategoryId(int id);
		
//		public List<Product> getProduct(long id);
		public List<Product> getProductByKeyword(String keyword);
		public int givePage();
}
