package com.rajan.aumsapi.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rajan.aumsapi.dao.Impl.TrainingMaterialDaoImpl;
import com.rajan.aumsapi.models.TrainingMaterial;
import com.rajan.aumsapi.services.TrainingMaterialService;

@Service
public class TrainingMaterialServiceImpl implements TrainingMaterialService{

	@Autowired
	TrainingMaterialDaoImpl materialdao;
	
	@Override
	public List<TrainingMaterial> getAllMaterial() {
		return materialdao.getAllMaterial();
	}

	@Override
	public List<TrainingMaterial> getMaterialByCourseID(int id) {
		return materialdao.getMaterialByCourseID(id);
	}

	@Override
	public void addMaterial(MultipartFile file, int courseId) {
		materialdao.addMaterial(file, courseId);
		
	}

	@Override
	public void deleteMaterial(int id) {
		materialdao.deleteMaterial(id);
		
	}

	@Override
	public List<TrainingMaterial> getMaterialActive() {
		return materialdao.getMaterialActive();
	}

}
