package com.rajan.aumsapi.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rajan.aumsapi.models.Course;

public class CourseRowMapper implements RowMapper<Course>{

	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		Course course = new Course();
		course.setCourseID(rs.getInt("courseID"));
		course.setCourseName(rs.getString("courseName"));
		course.setCourseDesc(rs.getString("courseDesc"));
		course.setCourseSkills(rs.getString("courseSkills"));
		course.setCoursePrerequisites(rs.getString("coursePrerequisites"));
		course.setCourseLocation(rs.getString("courseLocation"));
		course.setLast_modified(rs.getTimestamp("last_modified"));
		return course;
	}
	

}
