package com.rajan.aumsapi.dao;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.rajan.aumsapi.dao.Impl.TrainerDaoImpl;
import com.rajan.aumsapi.models.Trainer;

@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
public class TrainerDaoTest {

	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private TrainerDaoImpl dao;
	
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
	public void addTrainerTest() {
		dao.addTrainer(t);
		String query = "INSERT INTO trainers(trainerID, courseID) VALUES (?,?)";
		
		doThrow(new RuntimeException()).when(jdbcTemplate).update(query,t.getTrainerID(),t.getCourseID());
		dao.addTrainer(t);
		verify(jdbcTemplate, times(2)).update(query,t.getTrainerID(),t.getCourseID());
	}
	
	@Test
	public void deletetrainer() {
		dao.deleteTrainer(10, 11);
		dao.deleteTrainer(10, 11);
		String query = "DELETE FROM trainers WHERE trainerID = ? AND courseID = ?";
		verify(jdbcTemplate, times(2)).update(query, 10, 11);
	}
}
