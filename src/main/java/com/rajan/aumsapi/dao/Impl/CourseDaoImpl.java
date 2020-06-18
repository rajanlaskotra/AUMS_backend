package com.rajan.aumsapi.dao.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rajan.aumsapi.LoggerConfig;
import com.rajan.aumsapi.dao.CourseDao;
import com.rajan.aumsapi.models.Course;
import com.rajan.aumsapi.rowmapper.CourseRowMapper;

@Transactional
@Repository
public class CourseDaoImpl implements CourseDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Course> getAllCourses() {
		String query = "SELECT * FROM courses";
		
		LoggerConfig.LOGGER.info("Fetched All Courses.");
		return jdbcTemplate.query(query, new CourseRowMapper());
	}

	@Override
	public Course getCourseByName(String name) {
		String query = "SELECT * FROM courses WHERE courseName = ?";
		return jdbcTemplate.queryForObject(query, new CourseRowMapper(), name); 
	}

	@Override
	public void addCourse(Course course) {
		String query = "INSERT INTO courses (courseName, courseDesc, courseSkills,coursePrerequisites,courseLocation)" +
						" VALUES (?,?,?,?,?)";
		jdbcTemplate.update(query, course.getCourseName(),course.getCourseDesc(),course.getCourseSkills(),
							course.getCoursePrerequisites(),course.getCourseLocation());
		
		LoggerConfig.LOGGER.info("New Course Added -> " + course.getCourseName());
	}

	@Override
	public void updateCourse(Course course) {
		String query = "UPDATE courses SET courseName = ?, courseDesc = ?, courseSkills = ?,coursePrerequisites = ?,courseLocation = ?" +
						" WHERE courseID = ?";
		jdbcTemplate.update(query, course.getCourseName(),course.getCourseDesc(),course.getCourseSkills(),
							course.getCoursePrerequisites(),course.getCourseLocation(), course.getCourseID());
		
		LoggerConfig.LOGGER.info("Course Updated -> " + course.getCourseName());
	}

	@Override
	public void deleteCourse(int id) {
		String query = "DELETE FROM courses WHERE courseID = ?";
		jdbcTemplate.update(query, id);
		
		LoggerConfig.LOGGER.info("Course Deleted -> " + id);
		
	}

}
