
package com.watchshop.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.watchshop.dto.ProductDTO;
import com.watchshop.entities.Category;
import com.watchshop.entities.Product;
import com.watchshop.services.CategoryService;
import com.watchshop.services.ProductService;

@Controller
public class AdminController {
	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	
	@Autowired
	CategoryService  categoryservice;
	
	@Autowired
	ProductService productserv;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories",categoryservice.getAllCategory());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category",new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String getCatAdd(@ModelAttribute("category")  Category category) {
		categoryservice.addCategory(category);
		return "redirect:/admin/categories";
	}
	
    @GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
				categoryservice.removeCategoryById(id);
				return "redirect:/admin/categories";
	
          }
    
    @GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id , Model model) {
    	Optional<Category> category  = categoryservice.getCategoryById(id);
    	if(category.isPresent()) {
    		model.addAttribute("category", category.get());
    		return "categoriesAdd";
    	}
    	else {
    		return "404";
    	}
		
	}
    
    // Product 
    
    @GetMapping("/admin/products")
    public String products(Model model) {
    	model.addAttribute("productDTO", productserv.getAllProduct(1, 5));
    	return "products";
    }
    
    
    @GetMapping("/admin/products/add")
    public String productAddGet(Model model) {
    	model.addAttribute("productDTO", new ProductDTO());
    	model.addAttribute("categories", categoryservice.getAllCategory());
    	return "productsAdd";
    }
    
    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,@RequestParam("productImage") MultipartFile file,@RequestParam("imgName") String imgName)throws IOException
    {
    	
  	  Product product =new Product();
  	  product.setId(productDTO.getId());
//  	  System.out.println(productDTO.getCategoryId());
  	  product.setName(productDTO.getName());
  	  product.setCategory(categoryservice.getCategoryById(productDTO.getCategoryId()).get());
  	  product.setPrice(productDTO.getPrice());
  	  product.setWeight(productDTO.getWeight());
  	  product.setDescription(productDTO.getDescription());
  	  String imageUUID;
//  	 model.addAttribute("products",product);    
  	  if(!file.isEmpty()) {
  		  imageUUID=file.getOriginalFilename();
  		  Path fileNameAndPath=Paths.get(uploadDir,imageUUID);
  		  Files.write(fileNameAndPath,file.getBytes());
  		  
  	  }
  	  else {
  		  imageUUID=imgName;
  	  }
  	  product.setImageName(imageUUID);
  	  productserv.addProduct(product);
  	  
  	  return "redirect:/admin/products";
  	  
    }
    
//    @GetMapping("/admin/product/delete/{id}")
//    public  String deleteProductGet(@PathVariable long id) {
//    	productserv.removeProductById(id);
//    	return "redirect:/admin/products";
//    }
    
//    @GetMapping("/admin/product/update/{id}")
//    public  String updateProductGet(@PathVariable long id,Model model) {
//    	Product product =productserv.getProductById(id).get();
//    	ProductDTO productDTO =new ProductDTO();
//    	productDTO.setId(product.getId());
//    	productDTO.setName(product.getName());
//    	productDTO.setDescription(product.getDescription());
//    	productDTO.setCategory(product.getCategory().getId());
//    	productDTO.setPrice(product.getPrice());
//    	productDTO.setWeight(product.getWeight());
//    	productDTO.setImageName(product.getImageName());
//    	
//    	model.addAttribute("productDTO", productDTO);
//    	model.addAttribute("categories", categoryservice.getAllCategory());
//    	
////    	productserv.removeProductById(id);
//    	return "redirect:/admin/products";
//    }
    
	
}
	
	

