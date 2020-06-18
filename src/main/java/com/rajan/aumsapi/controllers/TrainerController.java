package com.rajan.aumsapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajan.aumsapi.models.Trainer;
import com.rajan.aumsapi.services.Impl.TrainerServiceImpl;

@RestController
@RequestMapping("api/trainer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class TrainerController {
	
	@Autowired
	TrainerServiceImpl trainerService;
	
	@GetMapping(value= "/")
	public List<Trainer> getAllTrainers() {
		return trainerService.getAllTrainers();
	}
	
	@GetMapping(value= "/{id}")
	public Trainer getTrainerByCourseID(@PathVariable("id") int id) {
		return trainerService.getTrainerByCourseID(id);
	}
	
	@PostMapping(value= "/add")
	public void addTrainer(@RequestBody Trainer trainer) {
		trainerService.addTrainer(trainer);
	}
	
	@DeleteMapping(value= "/delete/{tid}/{cid}")
	public void deleteTrainer(@PathVariable("tid") int tid, @PathVariable("cid") int cid) {
		trainerService.deleteTrainer(tid,cid);
	}
}
