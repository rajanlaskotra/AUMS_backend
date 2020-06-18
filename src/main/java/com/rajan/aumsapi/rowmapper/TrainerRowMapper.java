package com.rajan.aumsapi.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rajan.aumsapi.models.Trainer;

public class TrainerRowMapper implements RowMapper<Trainer>{

	@Override
	public Trainer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Trainer trainer = new Trainer();
		trainer.setUserID(rs.getInt("userID"));
		trainer.setUserName(rs.getString("userName"));
		trainer.setEmail(rs.getString("email"));
		trainer.setUserLocation(rs.getString("userLocation"));
		trainer.setTrainerID(rs.getInt("trainerID"));
		trainer.setCourseID(rs.getInt("courseID"));
		trainer.setFeedback(rs.getString("feedback"));
		trainer.setCourseName(rs.getString("courseName"));
		return trainer;
	}

}
