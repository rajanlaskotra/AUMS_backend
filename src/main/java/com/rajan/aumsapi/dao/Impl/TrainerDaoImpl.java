package com.rajan.aumsapi.dao.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rajan.aumsapi.LoggerConfig;
import com.rajan.aumsapi.dao.TrainerDao;
import com.rajan.aumsapi.models.Trainer;
import com.rajan.aumsapi.rowmapper.TrainerRowMapper;

@Transactional
@Repository
public class TrainerDaoImpl implements TrainerDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Trainer> getAllTrainers() {
		//String query = "SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID";
		String query = "SELECT * FROM courses JOIN " + 
				"(SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID) AS trainer " +
				"ON courses.courseID = trainer.courseID ";
		
		LoggerConfig.LOGGER.info("Fetched All Trainers.");
		return jdbcTemplate.query(query, new TrainerRowMapper());
	}

	@Override
	public Trainer getTrainerByCourseID(int id) {
		String query = "SELECT * FROM courses JOIN " + 
						"(SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID) AS trainer " +
						"ON courses.courseID = trainer.courseID WHERE courses.courseID = ?"; 
				
		return jdbcTemplate.queryForObject(query, new TrainerRowMapper(), id);
	}

	@Override
	public void addTrainer(Trainer trainer) {
		String query = "INSERT INTO trainers(trainerID, courseID) VALUES (?,?)";
		try {
			jdbcTemplate.update(query, trainer.getTrainerID(),trainer.getCourseID());
			LoggerConfig.LOGGER.info("New Trainer Added -> " + trainer.getTrainerID());
		}
		catch(Exception e) {
			LoggerConfig.LOGGER.error("Error Adding New Trainer -> " + trainer.getTrainerID());
			e.getMessage();
		}
	}

	@Override
	public void deleteTrainer(int tid, int cid) {
		String query = "DELETE FROM trainers WHERE trainerID = ? AND courseID = ?";
		jdbcTemplate.update(query, tid, cid);
		
		LoggerConfig.LOGGER.info("Deleted Trainer -> " + tid);
	}

}
