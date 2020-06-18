package com.rajan.aumsapi.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.rajan.aumsapi.dao.UserDao;
import com.rajan.aumsapi.dao.Impl.UserDaoImpl;
import com.rajan.aumsapi.models.User;
import com.rajan.aumsapi.services.Impl.UserServiceImpl;

@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
public class UserServiceTest {
	
	@Mock
	UserDaoImpl userDao;
	
	@InjectMocks
	UserServiceImpl userService;
	
	User u = new User();
	User u1 = new User();
	List<User> list = new ArrayList<>();
	
	@BeforeEach
	public void init() {
		u.setUserName("Rajan");
		u.setEmail("rajan@gmail.com");
		
		u1.setUserName("Tanish");
		u1.setEmail("tanish12@gmail.com");
		list.add(u);
		list.add(u1);
	}
	
	@Test
	public void getAllUserTest() {
		when(userDao.getAllUsers()).thenReturn(list);
		
		List<User> users = userService.getAllUsers();
		assertEquals(2, users.size());
	}

	@Test
	public void getUserByEmailTest() {
		String email = "rajan@gmail.com";
		when(userDao.getUserByEmail(email)).thenReturn(u);
		
		User user = userService.getUserByEmail(email);
		assertEquals(user.getUserName(), "Rajan");
	}
	
	@Test
	public void addUserTest() {
		userService.addUser(u);
		
		verify(userDao).addUser(u);
	}
	
	@Test
	public void deleteUserTest() {
		int id = 1;
		userService.deleteUser(id);
		
		verify(userDao).deleteUser(id);
	}
}
