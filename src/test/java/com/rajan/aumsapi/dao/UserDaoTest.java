package com.rajan.aumsapi.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.rajan.aumsapi.dao.Impl.UserDaoImpl;
import com.rajan.aumsapi.models.User;
import com.rajan.aumsapi.rowmapper.UserRowMapper;

@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
public class UserDaoTest {

	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private UserDaoImpl dao;
	
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
/*	
	@Test
	public void getAllUserTest() {
		String query = "SELECT * FROM users";
		when(jdbcTemplate.query(query, new UserRowMapper())).thenReturn(list);
		
		List<User> users = dao.getAllUsers();
		assertEquals(2, users.size());
	}

	@Test
	public void getUserByEmailTest() {
		String email = "rajan@gmail.com";
		String query = "SELECT * FROM users WHERE email = ?";
		when(jdbcTemplate.queryForObject(query, new UserRowMapper(), email)).thenReturn(u);
		
		User user = dao.getUserByEmail(email);
		assertEquals(user.getUserName(), "Rajan");
	}
*/	
	@Test
	public void addUserTest() {
		dao.addUser(u);
		String query = "INSERT INTO users (userName,email,userLocation) VALUES (?,?,?)";
		verify(jdbcTemplate).update(query, u.getUserName(),u.getEmail(),u.getUserLocation());
	}
	
	@Test
	public void deleteUserTest() {
		int id = 1;
		dao.deleteUser(id);
		dao.deleteUser(id);
		String query = "DELETE FROM users WHERE userID = ?";
		verify(jdbcTemplate, times(2)).update(query, id);
	}
}
