package com.rajan.aumsapi.dao.Impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.rajan.aumsapi.LoggerConfig;
import com.rajan.aumsapi.dao.TrainingMaterialDao;
import com.rajan.aumsapi.models.TrainingMaterial;
import com.rajan.aumsapi.rowmapper.TrainingMaterialRowMapper;

@Repository
public class TrainingMaterialDaoImpl implements TrainingMaterialDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//Fetches all old and new materials
	@Override
	public List<TrainingMaterial> getAllMaterial() {
		String query = "SELECT * FROM training_materials";
		
		LoggerConfig.LOGGER.info("Fetched All Training Material.");
		return jdbcTemplate.query(query, new TrainingMaterialRowMapper());
	}
	
	//Fetches only new material
	@Override
	public List<TrainingMaterial> getMaterialActive() {
		String query = "SELECT * FROM training_materials WHERE active_flag = ? AND status = ?";
		
		LoggerConfig.LOGGER.info("Fetched Active Training Material.");
		return jdbcTemplate.query(query, new TrainingMaterialRowMapper(), "Y", "New");
	}

	//Fetches only new material for particular course
	@Override
	public List<TrainingMaterial> getMaterialByCourseID(int id) {
		String query = "SELECT * FROM training_materials WHERE courseId = ? AND active_flag = ?";
		return jdbcTemplate.query(query, new TrainingMaterialRowMapper(), id, "Y");
	}

	@Override
	public void addMaterial(MultipartFile file, int courseId) {
		String prequery = "UPDATE training_materials SET active_flag = ?, status = ? WHERE courseId = ? AND active_flag = ?";
		jdbcTemplate.update(prequery, "N","Old",courseId,"Y");
		
		String query = "INSERT INTO training_materials(courseId, fileName,fileType,file,active_flag,status) VALUES (?,?,?,?,?,?)";
		
		try {
			
			byte[] bytes = file.getBytes();
			Blob blob;
			blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			jdbcTemplate.update(query, courseId,file.getOriginalFilename(),file.getContentType(), blob,"Y","New");
			
			LoggerConfig.LOGGER.info("Added New Material -> " + courseId);
			
		} catch (Exception e) {
			LoggerConfig.LOGGER.error("Error Adding New Material");
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMaterial(int id) {
		String query = "UPDATE training_materials SET active_flag = ?, status = ? WHERE materialID = ?";
		jdbcTemplate.update(query, "N","Deleted",id);
		
		LoggerConfig.LOGGER.info("Deleted Material -> " + id);
		
	}

}
