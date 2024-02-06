package com.watchshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.watchshop.dto.ProductDTO;
import com.watchshop.entities.Product;
import com.watchshop.repositories.ProductRepository;

@Service
public class ProductServiceImplementation implements ProductService {
	@Autowired
	ProductRepository productrepo;
	int pageNumber1;
	
	@Override
	public List<Product> getAllProduct(Integer pageNumber,Integer pageSize) {
//		int  pageSize=5;
//		int pageNumber =1;
		
		Pageable p =PageRequest.of(pageNumber,pageSize);	
		Page<Product> pagePost=productrepo.findAll(p);
		 List<Product> allProduct=pagePost.getContent();
		 pageNumber1=pageNumber;
		 
		   
		return allProduct;
	}

	@Override
	public void addProduct(Product product) {
		productrepo.save(product);
		
	}

	@Override
	public void removeProductById(long Id) {
		productrepo.deleteById(Id);
		
	}

	@Override
	public Optional<Product> getProductById(long id) {
		// TODO Auto-generated method stub
	
		return productrepo.findById(id);
	}

	@Override
	public List<Product> getProductByKeyword(String name) {
	   List<Product> productCon= productrepo.findByNameContaining(name);
		return productCon;
	}

	@Override
	public int givePage() {
		// TODO Auto-generated method stub
		return pageNumber1;
	}

//	@Override
//	public List<Product> getAllProductByCategoryId(int id) {
//		productrepo.findAllByCategory_Id(id);
//		return null;
//	}
//	
  




}
