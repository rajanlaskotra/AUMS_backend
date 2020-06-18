package com.rajan.aumsapi.models;


import java.sql.Timestamp;


public class TrainingMaterial {

	private int materialID;
	private int courseID;
	private String fileName;
	private String fileType;
	private byte[] file;
	private String active_flag;
	private String status;
	private Timestamp last_modified;
	
	
	public TrainingMaterial() {
	}

	public TrainingMaterial(int courseID, String fileName, String fileType, byte[] file, String active_flag,
			String status, Timestamp last_modified) {
		this.courseID = courseID;
		this.fileName = fileName;
		this.fileType = fileType;
		this.file = file;
		this.active_flag = active_flag;
		this.status = status;
		this.last_modified = last_modified;
	}


	public int getMaterialID() {
		return materialID;
	}

	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}

	public int getCourseID() {
		return courseID;
	}


	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public byte[] getFile() {
		return file;
	}


	public void setFile(byte[] file) {
		this.file = file;
	}


	public String getActive_flag() {
		return active_flag;
	}


	public void setActive_flag(String active_flag) {
		this.active_flag = active_flag;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Timestamp getLast_modified() {
		return last_modified;
	}


	public void setLast_modified(Timestamp last_modified) {
		this.last_modified = last_modified;
	}
		
	
}
