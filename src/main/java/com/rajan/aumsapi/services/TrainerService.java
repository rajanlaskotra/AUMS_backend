package com.rajan.aumsapi.services;

import java.util.List;

import com.rajan.aumsapi.models.Trainer;

public interface TrainerService {
	
	public List<Trainer> getAllTrainers();
	
	public Trainer getTrainerByCourseID(int id);
	
	public void addTrainer(Trainer trainer);
	
	public void deleteTrainer(int tid,int cid);
}
