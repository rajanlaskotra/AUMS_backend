package com.rajan.aumsapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rajan.aumsapi.models.TrainingMaterial;
import com.rajan.aumsapi.services.Impl.TrainingMaterialServiceImpl;

@RestController
@RequestMapping("api/material")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class TrainingMaterialController {

	@Autowired
	TrainingMaterialServiceImpl materialService;
	
	@GetMapping(value= "/")
	public List<TrainingMaterial> getAllMaterial() {
		return materialService.getAllMaterial();
	}
	
	@GetMapping(value= "/active")
	public List<TrainingMaterial> getMaterialActive() {
		return materialService.getMaterialActive();
	}
	
	@GetMapping(value= "/{id}")
	public List<TrainingMaterial> getMaterialByCourseID(@PathVariable("id") int id) {
		return materialService.getMaterialByCourseID(id);
	}
	
	@PostMapping(value= "/add")
	@ResponseBody
	public void addMaterial(@RequestParam("file") MultipartFile file, @RequestParam("courseId") int courseId) {
		materialService.addMaterial(file, courseId);
	}
	
	@DeleteMapping(value= "/delete/{mid}")
	public void deleteMaterial(@PathVariable("mid") int mid) {
		materialService.deleteMaterial(mid);
	}
}
