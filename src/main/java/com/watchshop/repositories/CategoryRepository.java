package com.watchshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watchshop.entities.Category;

public interface CategoryRepository  extends JpaRepository<Category,Integer>{

}
