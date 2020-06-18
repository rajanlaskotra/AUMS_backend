package com.rajan.aumsapi.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rajan.aumsapi.LoggerConfig;
import com.rajan.aumsapi.dao.UserDao;
import com.rajan.aumsapi.models.User;
import com.rajan.aumsapi.rowmapper.UserRowMapper;

@Transactional
@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<User> getAllUsers() {
		String query = "SELECT * FROM users";
		List<User> users = new ArrayList<>();
		users = jdbcTemplate.query(query, new UserRowMapper());
		return users;
	}

	@Override
	public User getUserByEmail(String email) {
		String query = "SELECT * FROM users WHERE email = ?";
		try {
			return jdbcTemplate.queryForObject(query, new UserRowMapper(), email);
		}
		catch(DataAccessException e) {
			LoggerConfig.LOGGER.error("User Not Valid! => " + email);
		}
		return null;
	}

	@Override
	public void addUser(User user) {
		String query = "INSERT INTO users (userName,email,userLocation) VALUES (?,?,?)";
		jdbcTemplate.update(query, user.getUserName(),user.getEmail(),user.getUserLocation());
	}

	@Override
	public void deleteUser(int id) {
		String query = "DELETE FROM users WHERE userID = ?";
		jdbcTemplate.update(query, id);
		
	}
	
	
}
