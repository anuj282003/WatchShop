package com.watchshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.watchshop.entities.User;
//import com.watchshop.repositories.UserRepositories;
import com.watchshop.services.CategoryService;
import com.watchshop.services.ProductService;
//import com.watchshop.services.UserService;

@Controller
public class HomeController {
	Page page;
//	
//	@Autowired
//	UserService userserv;
//	
//	@Autowired
//	UserRepositories userRepo;
//	
//	@GetMapping("/map-register")
//	public String mapRegister(){
//		return "registerPage";
//	}
//	
//	@PostMapping("/registerdata")
//	public String registerUser(@ModelAttribute User user) {
//	   userserv.addUser(user);
//		return "registerSuccess";
//	}
//	
//
//	@GetMapping("/map-login")
//	public String mapLogin() {
//		return "loginpage";
//	}
//	
//	@PostMapping("/loginData")
//	public String loginUser(@RequestParam("email") String email ,@RequestParam("password") String password) {
//		boolean status= userserv.checkUser(email,password);
//		if(status) {
//			if(userRepo.findByEmail(email).getRole().equals("seller")) {
//				return "sellerpage";
//			}
//			else {
//				return "index";
//			}
//		}
//		return null;
//		
//	}
	
	@Autowired
	CategoryService categoryservice;
	@Autowired
	ProductService prdserv;
	
	@GetMapping({"/","/home"})
	public String home( Model model)
	{
			return "index";
	}
	
	@GetMapping("/shop")
	public String shop (Model model,
						@RequestParam(value="pageNumber",defaultValue="0" ,required=false) Integer pageNumber,
						@RequestParam(value="pageSize" ,defaultValue="5", required=false) Integer pageSize) {
		model.addAttribute("categories", categoryservice.getAllCategory());
		model.addAttribute("products",prdserv.getAllProduct(pageNumber,pageSize) );
//		model.addAttribute("pageNumber", page.getTotalPages());
		model.addAttribute("pageNumber",prdserv.givePage());
		System.out.println(prdserv.givePage());
		
		return "shop";
	}
	
	@GetMapping("/shop/{pageNumber}")
	public String pageshop(Model model,
						@PathVariable("pageNumber") int pageNumber) {
//		int val=pageNumber;
		int pageSize =5;
		model.addAttribute("categories", categoryservice.getAllCategory());
		model.addAttribute("products",prdserv.getAllProduct(pageNumber,pageSize) );
		System.out.println(prdserv.givePage());
//		System.out.println(val);
		
		return "shop";
	}
	
	
	
	
//	@GetMapping("/shop/category/{id}")
//	public String shopByCategory(Model model,@PathVariable int id) {
//		model.addAttribute("categories", categoryservice.getAllCategory());
//		model.addAttribute("products",prdserv.getProductById(id) );
//		return "shop";
//	}
//	
	 
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable int id) {
		model.addAttribute("product",prdserv.getProductById(id).get() );
		return "viewProduct";
	}
	
//	@GetMapping("/addToCart/{id}")
//	public String addTOCart() {
//		return "cart";
//	}
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam("keyword") String keyword,Model model) {
		model.addAttribute("data",prdserv.getProductByKeyword(keyword));
//		System.out.println(prdserv.getProductByKeyword(keyword));
		return "filterdata";
	}

}
