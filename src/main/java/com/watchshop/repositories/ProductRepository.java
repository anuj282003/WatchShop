package com.watchshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watchshop.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

	List<Product> findAllByCategory_Id(int id);

	List<Product> findByNameContaining(String name);
	
	

}
