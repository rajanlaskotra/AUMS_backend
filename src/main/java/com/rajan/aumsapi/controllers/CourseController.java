package com.rajan.aumsapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajan.aumsapi.models.Course;
import com.rajan.aumsapi.services.Impl.CourseServiceImpl;

@RestController
@RequestMapping("api/course")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class CourseController {

	@Autowired
	CourseServiceImpl courseService;
	
	@GetMapping(value= "/")
	public List<Course> getAllCourses(){
		return courseService.getAllCourses();
	}
	
	@GetMapping(value= "/{name}")
	public Course getCourseByName(@PathVariable("name") String name) {
		return courseService.getCourseByName(name);
	}
	
	@PostMapping(value= "/add")
	public void addCourse(@RequestBody Course course) {
		courseService.addCourse(course);
	}
	
	@PutMapping(value= "/update")
	public void updateCourse(@RequestBody Course course) {
		courseService.updateCourse(course);
	}
	
	@DeleteMapping(value= "/delete/{id}")
	public void deleteCourse(@PathVariable("id") int id) {
		courseService.deleteCourse(id);
	}
}
