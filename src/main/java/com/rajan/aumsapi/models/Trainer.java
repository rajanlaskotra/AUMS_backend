package com.rajan.aumsapi.models;

public class Trainer extends User{

	private int trainerID;
	private int courseID;
	private String courseName; 
	private String feedback;
	
	
	public Trainer() {
		super();
	}
	public Trainer(int trainerID, int courseID, String feedback) {
		this.trainerID = trainerID;
		this.courseID = courseID;
		this.feedback = feedback;
	}
	public int getTrainerID() {
		return trainerID;
	}
	public void setTrainerID(int trainerID) {
		this.trainerID = trainerID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	} 
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
