package com.rajan.aumsapi.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rajan.aumsapi.models.TrainingMaterial;

public class TrainingMaterialRowMapper implements RowMapper<TrainingMaterial>{

	@Override
	public TrainingMaterial mapRow(ResultSet rs, int rowNum) throws SQLException {
		TrainingMaterial material = new TrainingMaterial();
		
		material.setMaterialID(rs.getInt("materialID"));
		material.setCourseID(rs.getInt("courseId"));
		material.setFileName(rs.getString("fileName"));
		material.setFileType(rs.getString("fileType"));
		material.setFile(rs.getBlob("file").getBytes(1, (int)rs.getBlob("file").length()));	
		material.setActive_flag(rs.getString("active_flag"));
		material.setStatus(rs.getString("status"));
		material.setLast_modified(rs.getTimestamp("last_modified"));
		
		return material;
	}

}
