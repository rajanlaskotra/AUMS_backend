package com.rajan.aumsapi.dao;

import java.util.List;

import com.rajan.aumsapi.models.Trainer;

public interface TrainerDao {

	public List<Trainer> getAllTrainers();
	
	public Trainer getTrainerByCourseID(int id);
	
	public void addTrainer(Trainer trainer);
	
	public void deleteTrainer(int tid, int cid);
}
