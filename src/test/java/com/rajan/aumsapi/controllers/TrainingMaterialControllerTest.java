package com.rajan.aumsapi.controllers;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.DnsSrv.SrvRecord;
import com.rajan.aumsapi.models.TrainingMaterial;
import com.rajan.aumsapi.services.Impl.TrainingMaterialServiceImpl;

@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
@AutoConfigureMockMvc
public class TrainingMaterialControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	TrainingMaterialServiceImpl service;
	
	TrainingMaterial t1 = new TrainingMaterial();
	TrainingMaterial t2 = new TrainingMaterial();
	List<TrainingMaterial> list = new ArrayList<>();
	
	@BeforeEach
	public void init() {
		t1.setMaterialID(1);
		t1.setCourseID(8);
		t1.setFileName("file");
		t1.setFileType("txt");
		t1.setStatus("New");
		
		t2.setMaterialID(2);
		t2.setCourseID(10);
		t2.setFileName("file2");
		t2.setFileType("pdf");
		t2.setStatus("New");
		
		list.add(t1);
		list.add(t2);
		
	}
	
	@Test
	public void getAllMaterial() throws Exception {
		when(service.getAllMaterial()).thenReturn(list);
		
		mock.perform(get("/api/material/"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()", is(2)));
	}

	@Test
	public void getMaterialActive() throws Exception {
		when(service.getMaterialActive()).thenReturn(list);
		
		mock.perform(get("/api/material/active"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].status").value("New"));
	}
	
	@Test
	public void getMaterialByCourseIdTest() throws Exception {
		list.clear();
		list.add(t2);
		when(service.getMaterialByCourseID(10)).thenReturn(list);
		
		mock.perform(get("/api/material/{id}", 10))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].materialID").value(2))
			.andExpect(jsonPath("$.length()", is(1)));
	}
	
	@Test
	public void deleteMaterialTest() throws Exception {
		mock.perform(delete("/api/material/delete/{id}",1)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	
}
