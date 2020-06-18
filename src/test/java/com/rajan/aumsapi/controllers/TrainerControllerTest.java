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
import com.rajan.aumsapi.models.Trainer;
import com.rajan.aumsapi.services.Impl.TrainerServiceImpl;

@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
@AutoConfigureMockMvc
public class TrainerControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	TrainerServiceImpl service;
	
	Trainer t1 = new Trainer();
	Trainer t2 = new Trainer();
	List<Trainer> list = new ArrayList<>();
	
	@BeforeEach
	public void init() {
		t1.setTrainerID(1);
		t1.setCourseID(11);
		
		t2.setCourseID(22);
		t2.setTrainerID(2);
		list.add(t1);
		list.add(t2);
	}
	
	@Test
	public void getAllTrainerTest() throws Exception {
		when(service.getAllTrainers()).thenReturn(list);
		
		mock.perform(get("/api/trainer/"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()", is(2)));
		
	}
	
	@Test
	public void getTarinerByCourseId() throws Exception {
		when(service.getTrainerByCourseID(11)).thenReturn(t1);
		
		mock.perform(get("/api/trainer/{id}", 11))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.trainerID").value(1));
		
	}
	
	@Test
	public void addTrainerTest() throws Exception {
		mock.perform(post("/api/trainer/add")
				.content(asJsonString(t2))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void deleteTrainer() throws Exception {
		mock.perform(delete("/api/trainer/delete/{tid}/{cid}",2,22)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
}
}
