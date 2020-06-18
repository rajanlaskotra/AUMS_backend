package com.rajan.aumsapi.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajan.aumsapi.models.Course;
import com.rajan.aumsapi.services.Impl.CourseServiceImpl;

@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
//@WebMvcTest(CourseController.class)
@AutoConfigureMockMvc
public class CourseControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private CourseServiceImpl service;
	
	List<Course> list = new ArrayList<>();
	Course c1 = new Course();
	Course c2 = new Course();
	
	@BeforeEach
	public void init() {
		c1.setCourseID(1);
		c1.setCourseName("Angular");
		c1.setCourseLocation("Mumbai");
		
		c2.setCourseID(2);
		c2.setCourseName("Spring");
		c2.setCourseLocation("Bangalore");
		list.add(c1);
		list.add(c2);
	}
	
	@Test
	public void getAllCourseTest() throws Exception {
		when(service.getAllCourses()).thenReturn(list);
		
		mock.perform(get("/api/course/"))
				.andDo(print())
	    		.andExpect(status().isOk())
	    		.andExpect(jsonPath("$.length()",is(2)));
		
		
	}
	
	@Test
	public void getCourseByName() throws Exception {
		when(service.getCourseByName("Angular")).thenReturn(c1);
		
		mock.perform(get("/api/course/{name}", "Angular"))
				.andDo(print())
	    		.andExpect(status().isOk())
	    		.andExpect(jsonPath("$.courseID").value(1));
	
	}
	
	@Test
	public void addCourseTest() throws Exception {
		
		mock.perform(post("/api/course/add")
			    .content(asJsonString(c2))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void updateCourseTest() throws Exception{
		c1.setCourseID(1);
		c1.setCourseName("Angular");
		c1.setCourseLocation("Delhi");
		mock.perform(put("/api/course/update")
			    .content(asJsonString(c1))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())	
				.andReturn();
	}
	
	@Test
	public void deleteCourseTest() throws Exception {
		mock.perform(delete("/api/course/delete/{id}",2)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
	        return new ObjectMapper().writeValueAsString(obj);
	}
}
