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
import com.rajan.aumsapi.dao.Impl.TrainingMaterialDaoImpl;
import com.rajan.aumsapi.models.TrainingMaterial;

@SpringBootTest(classes= com.rajan.aumsapi.AumsApplication.class)
public class TrainingMaterialDaoTest {

	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private TrainingMaterialDaoImpl dao;
	
	@Test
	public void deleteMaterailTest() {
		int id = 1;
		dao.deleteMaterial(id);
		dao.deleteMaterial(id);
		
		String query = "UPDATE training_materials SET active_flag = ?, status = ? WHERE materialID = ?";
		verify(jdbcTemplate, times(2)).update(query, "N","Deleted",id);
	}
}
