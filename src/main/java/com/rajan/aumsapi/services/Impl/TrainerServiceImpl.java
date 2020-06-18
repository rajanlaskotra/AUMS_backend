package com.rajan.aumsapi.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajan.aumsapi.dao.Impl.TrainerDaoImpl;
import com.rajan.aumsapi.models.Trainer;
import com.rajan.aumsapi.services.TrainerService;

@Service
public class TrainerServiceImpl implements TrainerService{

	@Autowired
	TrainerDaoImpl trainerdao;
	
	@Override
	public List<Trainer> getAllTrainers() {
		return trainerdao.getAllTrainers();
	}

	@Override
	public Trainer getTrainerByCourseID(int id) {
		return trainerdao.getTrainerByCourseID(id);
	}

	@Override
	public void addTrainer(Trainer trainer) {
		trainerdao.addTrainer(trainer);
		
	}

	@Override
	public void deleteTrainer(int tid, int cid) {
		trainerdao.deleteTrainer(tid, cid);
		
	}

}
