package com.rajan.aumsapi.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajan.aumsapi.dao.Impl.UserDaoImpl;
import com.rajan.aumsapi.models.User;
import com.rajan.aumsapi.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDaoImpl userdao;
	
	@Override
	public List<User> getAllUsers() {	
		return userdao.getAllUsers();
	}

	@Override
	public User getUserByEmail(String email) {
		return userdao.getUserByEmail(email);
	}

	@Override
	public void addUser(User user) {
		userdao.addUser(user);
	}

	@Override
	public void deleteUser(int id) {
		userdao.deleteUser(id);
		
	}
	 
}
