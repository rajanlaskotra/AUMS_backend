package com.rajan.aumsapi.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rajan.aumsapi.models.TrainingMaterial;

public interface TrainingMaterialDao {

	public List<TrainingMaterial> getAllMaterial();
	
	public List<TrainingMaterial> getMaterialActive();
	
	public List<TrainingMaterial> getMaterialByCourseID(int id);
	
	public void addMaterial(MultipartFile file, int courseId);
	
	public void deleteMaterial(int id);
}
