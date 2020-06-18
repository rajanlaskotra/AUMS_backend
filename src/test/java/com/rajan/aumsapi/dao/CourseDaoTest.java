package com.rajan.aumsapi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import com.rajan.aumsapi.dao.Impl.CourseDaoImpl;
import com.rajan.aumsapi.models.Course;
import com.rajan.aumsapi.rowmapper.CourseRowMapper;

@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
public class CourseDaoTest {

	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private CourseDaoImpl dao;
	
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
	}
	
	@Test
	public void getAllCoursesTest() {
		String query = "SELECT * FROM courses";
		when(jdbcTemplate.query(query, new CourseRowMapper())).thenReturn(courseList);
		
		List<Course> courseList = dao.getAllCourses();
		assertNotNull(courseList);
		//assertEquals(2, courseList.size());
		//verify(jdbcTemplate).query(query, new CourseRowMapper());
		
	}
/*	
	@Test
	public void getCourseByNameTest() {
		String name = "Spring";
		when(jdbcTemplate.queryForObject("SELECT * FROM courses WHERE courseName = ?", new CourseRowMapper(), name)).thenReturn(c1);
		
		Course course = dao.getCourseByName(name);
		assertNotNull(course);
		assertEquals(course.getCourseName(),"Spring");
		assertEquals(course.getCourseID(), 3);
	}
*/	
	@Test
	public void addCourseTest() {
		Course course = new Course();
		course.setCourseName("Java");
		course.setCourseID(4);
		course.setCourseLocation("Mumbai");
		
		String query = "INSERT INTO courses (courseName, courseDesc, courseSkills,coursePrerequisites,courseLocation)" +
				" VALUES (?,?,?,?,?)";
		
		when(jdbcTemplate.update(query, course.getCourseName(),course.getCourseDesc(),course.getCourseSkills(),
							course.getCoursePrerequisites(),course.getCourseLocation())).thenReturn(1);
		
		dao.addCourse(course);
		verify(jdbcTemplate).update(query, course.getCourseName(),course.getCourseDesc(),course.getCourseSkills(),
							course.getCoursePrerequisites(),course.getCourseLocation());
	}
	
	@Test
	public void updateCourseTest() {
		String query = "UPDATE courses SET courseName = ?, courseDesc = ?, courseSkills = ?,coursePrerequisites = ?,courseLocation = ?" +
				" WHERE courseID = ?";
		when(jdbcTemplate.update(query, c.getCourseName(),c.getCourseDesc(),c.getCourseSkills(),
				c.getCoursePrerequisites(),c.getCourseLocation(), c.getCourseID())).thenReturn(1);
		
		dao.updateCourse(c);
		verify(jdbcTemplate).update(query, c.getCourseName(),c.getCourseDesc(),c.getCourseSkills(),
				c.getCoursePrerequisites(),c.getCourseLocation(), c.getCourseID());
	}
	
	@Test
	public void deleteCourseTest() {
		int id=1;
		dao.deleteCourse(1);
		dao.deleteCourse(1);
		String query = "DELETE FROM courses WHERE courseID = ?";
		verify(jdbcTemplate, times(2)).update(query, id);
	}

}
