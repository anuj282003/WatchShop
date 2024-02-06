//package com.watchshop.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.watchshop.entities.User;
//import com.watchshop.repositories.UserRepositories;
//
//@Service
//public class UserServiceImplementation implements UserService
//{
//	  @Autowired
//       UserRepositories userRepo;
//	  
//	  
//	  public String addUser(User user) {
//		  userRepo.save(user);
//		  return "msg";
//	  }
//		
//	  public boolean checkUser(String email,String password) {
//		  
//		  User userdb =userRepo.findByEmail(email);
//		  if(userdb!=null) {
//			  if(password.equals(userdb.getPassword())) {
//				  
//				  return true;
//			  }
//		  }
//		return false;
//	
//	  }
//		
//}
