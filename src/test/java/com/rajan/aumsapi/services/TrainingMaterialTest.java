package com.rajan.aumsapi.services;

import static org.junit.Assert.assertEquals;
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
import org.springframework.web.multipart.MultipartFile;

import com.rajan.aumsapi.dao.Impl.TrainingMaterialDaoImpl;
import com.rajan.aumsapi.models.TrainingMaterial;
import com.rajan.aumsapi.services.Impl.TrainingMaterialServiceImpl;

@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
public class TrainingMaterialTest {
	
	@Mock
	TrainingMaterialDaoImpl dao;
	
	@InjectMocks
	TrainingMaterialServiceImpl service;
	
	TrainingMaterial t1 = new TrainingMaterial();
	TrainingMaterial t2 = new TrainingMaterial();
	List<TrainingMaterial> list = new ArrayList<>();
	
	@BeforeEach
	public void init() {
		t1.setCourseID(1);
		t1.setFileName("file1");
		t1.setFileType("txt");
		t1.setStatus("New");
		

		t2.setCourseID(2);
		t2.setFileName("file2");
		t2.setFileType("pdf");
		t2.setStatus("New");
		list.add(t1);
		list.add(t2);
	}
	
	@Test
	public void getAllTrainingMaterialTest() {
		when(dao.getAllMaterial()).thenReturn(list);
		
		List<TrainingMaterial> materials = service.getAllMaterial();
		assertEquals(materials.size(), 2);
		assertEquals(materials.get(1).getCourseID(), 2);
	}
	
	@Test
	public void getMaterialByCourseID() {
		int id = 1;
		when(dao.getMaterialByCourseID(id)).thenReturn(list);
		
		List<TrainingMaterial> materials = service.getMaterialByCourseID(id);
		assertEquals(materials.size(), 2);
		assertEquals(materials.get(0).getCourseID(), 1);
		
	}
	
	@Test
	public void deleteMaterailTest() {
		int id = 1;
		service.deleteMaterial(id);
		service.deleteMaterial(id);
		
		verify(dao, times(2)).deleteMaterial(id);
	}
	
	@Test
	public void getMaterialActive() {
		when(dao.getMaterialActive()).thenReturn(list);
		
		List<TrainingMaterial> actList = service.getMaterialActive();
		assertEquals(actList.size(), 2);
		assertEquals(actList.get(1).getFileName(), "file2");
	}
	
	@Test
	public void addMaterialTest() {
		MultipartFile file = null;
		int courseId = 1;
		
		service.addMaterial(file, courseId);
		verify(dao).addMaterial(file, courseId);
	}
	

}
