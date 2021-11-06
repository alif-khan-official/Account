package com.user.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.model.User;
import com.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers()   
	{  
		List<User> users = new ArrayList<User>();  
		userRepository.findAll().forEach(user->users.add(user));  
		return users;  
	}  
	
	public User getUserById(long id)   
	{  
		return userRepository.findById(id).get();  
	}  
	/*
	public void saveOrUpdateUser(User user)   
	{  
		userRepository.save(user);  
	}  
	*/
	
	public void saveOrUpdateUser(User user)   
	{  
		userRepository.save(user);  
	}  
	
	public void deleteUser(long id)   
	{  
		userRepository.deleteById(id);  
	}  
	
	/*
	public void updateUser(User user, long id)   
	{  
		userRepository.save(user);  
	}  
	*/

}
