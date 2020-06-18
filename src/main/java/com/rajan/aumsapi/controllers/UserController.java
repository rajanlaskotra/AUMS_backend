package com.rajan.aumsapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajan.aumsapi.models.User;
import com.rajan.aumsapi.services.Impl.UserServiceImpl;
import com.rajan.aumsapi.LoggerConfig;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping(value= "/")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping(value= "/{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		LoggerConfig.LOGGER.info("User Request LogIn ->" + email);
		return userService.getUserByEmail(email);
	}
	
	/*
	@PostMapping("/add")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
	} */
	
}
