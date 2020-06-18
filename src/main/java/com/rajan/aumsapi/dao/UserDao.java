package com.rajan.aumsapi.dao;

import java.util.List;

import com.rajan.aumsapi.models.User;

public interface UserDao {

	public List<User> getAllUsers();
	
	public User getUserByEmail(String email);
	
	public void addUser(User user);
	/*
	public void updateUser(User user); */
	
	public void deleteUser(int id); 
}
