package com.rajan.aumsapi.dao;

import java.util.List;

import com.rajan.aumsapi.models.Course;

public interface CourseDao {

	public List<Course> getAllCourses();
	
	public Course getCourseByName(String name);
	
	public void addCourse(Course course);
	
	public void updateCourse(Course course);
	
	public void deleteCourse(int id);
}
