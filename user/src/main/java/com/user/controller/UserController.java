package com.user.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.httpclient.HttpRequest;
import com.user.model.AddAccountInputModel;
import com.user.model.DeactivateAccountInputModel;
import com.user.model.DeactivateUserInputModel;
import com.user.model.UpdateAccountInputModel;
import com.user.model.UpdateUserInputModel;
import com.user.model.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	/*
	@Autowired
	private HttpRequest httpRequest;
	*/
	
	String s;
	
	HttpRequest h = new HttpRequest();
	
	@GetMapping("/users")  
	private List<User> getAllUsers()   
	{  
		return userService.getAllUsers();  
	}  
	
	@GetMapping("/user/{id}") 
	private User getUserById(@PathVariable("id") long id)   
	{  
		return userService.getUserById(id);  
	}  
	
	/*
	@DeleteMapping("/user/{id}")  
	private void deleteUser(@PathVariable("id") long id)   
	{  
		userService.deleteUser(id);  
	} 
	*/ 
	
	@PostMapping("/user")  
	private boolean addUser(@RequestBody User user)   
	{  
		userService.saveOrUpdateUser(user);  
		
		AddAccountInputModel a = new AddAccountInputModel();
		
		a.setUserId(user.getId());
		a.setAccountName(user.getUsername());
		a.setStatus(user.getStatus());		
		
		/*
		try {
			httpRequest.addAccount();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		try {
			s = h.addAccount(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.setAccountId(Long.parseLong(s));
		
		userService.saveOrUpdateUser(user);
		
		return true;  
	}  
	
	/*
	@PutMapping("/user")  
	// private User updateUser(@RequestBody User user)
	private long updateUser(@RequestBody User user)
	{  
		userService.saveOrUpdateUser(user);  
		// return user;
		return user.getId();  
	} 
	*/ 
	
	@PutMapping("/user")  
	private boolean updateUser(@RequestBody UpdateUserInputModel inputModel)
	{  
		User u = userService.getUserById(inputModel.getId());
		
		u.setUsername(inputModel.getUsername());
		
		userService.saveOrUpdateUser(u);  
		
		UpdateAccountInputModel ua = new UpdateAccountInputModel();
		
		ua.setId(u.getAccountId());
		ua.setAccountName(inputModel.getUsername());	
		
		try {
			s = h.updateAccount(ua);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;  
	} 
	
	@PutMapping("/user/deactive")  
	private boolean updateUser(@RequestBody DeactivateUserInputModel inputModel)
	{  
		User u = userService.getUserById(inputModel.getId());
		
		u.setStatus(false);
		
		userService.saveOrUpdateUser(u); 
		
		DeactivateAccountInputModel ua = new DeactivateAccountInputModel();
		
		ua.setId(u.getAccountId());
		
		try {
			s = h.deactivateAccount(ua);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;  
	}
}
