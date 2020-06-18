package com.rajan.aumsapi.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.rajan.aumsapi.models.User;
import com.rajan.aumsapi.services.Impl.UserServiceImpl;

@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private UserServiceImpl service;
	
	User u1 = new User();
	User u2 = new User();
	List<User> userList = new ArrayList<>();
	
	@BeforeEach
	public void init() {
		u1.setUserID(1);
		u1.setUserName("Rajan");
		u1.setEmail("rajan@gmail.com");
		
		u2.setUserID(2);
		u2.setUserName("Sahil");
		u2.setEmail("sahil@gmail.com");
		
		userList.add(u1);
		userList.add(u2);
	}
	
	@Test
	public void getAllUserTest() throws Exception {
		when(service.getAllUsers()).thenReturn(userList);
		
		mock.perform(get("/api/user/"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.length()", is(2)));
	}

	@Test
	public void getUserByEmailTest() throws Exception {
		when(service.getUserByEmail("rajan@gmail.com")).thenReturn(u1);
		
		mock.perform(get("/api/user/{email}", "rajan@gmail.com"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userID").value(1));
		
	}
}
