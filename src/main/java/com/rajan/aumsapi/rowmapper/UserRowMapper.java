package com.rajan.aumsapi.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rajan.aumsapi.models.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserID(rs.getInt("userID"));
		user.setUserName(rs.getString("userName"));
		user.setEmail(rs.getString("email"));
		user.setUserLocation(rs.getString("userLocation"));
		return user;
	}
}
