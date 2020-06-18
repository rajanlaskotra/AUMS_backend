package com.rajan.aumsapi.services;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.rajan.aumsapi.dao.Impl.CourseDaoImpl;
import com.rajan.aumsapi.models.Course;
import com.rajan.aumsapi.services.Impl.CourseServiceImpl;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
public class CourseServiceTest {

	@Mock
	CourseDaoImpl courseDao;
	
	@InjectMocks
	CourseServiceImpl courseService;
	
	List<Course> courseList = new ArrayList<>();
	Course c = new Course();
	Course c1 = new Course();
	
	@BeforeEach
	public void init() {
		c.setCourseID(1);
		c.setCourseName("Angular");
		c.setCourseDesc("Learn to build SPAs");
		
		c1.setCourseID(3);
		c1.setCourseName("Spring");
		c1.setCourseDesc("Learn backend");
		
		courseList.add(c);
		courseList.add(c1);
		System.out.println(courseList);
	}
	
	@Test
	public void getAllCoursesTest() {
		when(courseDao.getAllCourses()).thenReturn(courseList);
		
		List<Course> list = courseService.getAllCourses();
		assertEquals(2, list.size());
		
	}
	
	@Test
	public void getCourseByNameTest() {
		String name = "Spring";
		when(courseDao.getCourseByName(name)).thenReturn(c1);
		
		Course course = courseService.getCourseByName(name);
		assertEquals(course.getCourseName(),"Spring");
		assertEquals(course.getCourseID(), 3);
	}
	
	@Test
	public void addCourseTest() {
		Course course = new Course();
		course.setCourseName("Java");
		course.setCourseID(4);
		course.setCourseLocation("Mumbai");
		
		courseService.addCourse(course);
		verify(courseDao, times(1)).addCourse(course);
	}
	
	@Test
	public void updateCourseTest() {
		courseService.updateCourse(c);
		
		verify(courseDao, times(1)).updateCourse(c);
		
	}
	
	@Test
	public void deleteCourseTest() {
		int id=1;
		courseService.deleteCourse(1);
		courseService.deleteCourse(1);
		
		verify(courseDao, times(2)).deleteCourse(id);
	}
	
	
}
