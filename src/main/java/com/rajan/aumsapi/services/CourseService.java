package com.rajan.aumsapi.services;

import java.util.List;
import com.rajan.aumsapi.models.Course;

public interface CourseService {

	public List<Course> getAllCourses();
	
	public Course getCourseByName(String name);
	
	public void addCourse(Course course);
	
	public void updateCourse(Course course);
	
	public void deleteCourse(int id);
	
}
