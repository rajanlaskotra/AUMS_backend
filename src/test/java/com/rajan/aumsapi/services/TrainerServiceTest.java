package com.rajan.aumsapi.services;

import org.springframework.boot.test.context.SpringBootTest;

import com.rajan.aumsapi.dao.Impl.TrainerDaoImpl;
import com.rajan.aumsapi.models.Trainer;
import com.rajan.aumsapi.services.Impl.TrainerServiceImpl;

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



@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
public class TrainerServiceTest {

	@Mock
	TrainerDaoImpl dao;
	
	@InjectMocks
	TrainerServiceImpl service;
	
	List<Trainer> trainerList = new ArrayList<>();
	Trainer t = new Trainer();
	Trainer t1 = new Trainer();
	
	@BeforeEach
	public void init() {
		t.setTrainerID(1);
		t1.setCourseID(2);
		
		t1.setTrainerID(10);
		t1.setCourseID(11);
		trainerList.add(t);
		trainerList.add(t1);
	}
	
	@Test
	public void getAllTrainerTest() {
		when(dao.getAllTrainers()).thenReturn(trainerList);
		
		List<Trainer> list = service.getAllTrainers();
		assertEquals(list.size(), 2);
	}
	
	@Test
	public void getTrainerByCourseId() {
		when(dao.getTrainerByCourseID(11)).thenReturn(t1);
		
		Trainer trainer = service.getTrainerByCourseID(11);
		assertEquals(trainer.getTrainerID(), 10);
	}
	
	@Test
	public void addTrainerTest() {
		service.addTrainer(t);
		
		verify(dao).addTrainer(t);
	}
	
	@Test
	public void deletetrainer() {
		service.deleteTrainer(10, 11);
		service.deleteTrainer(10, 11);
		
		verify(dao, times(2)).deleteTrainer(10, 11);
	}
}
