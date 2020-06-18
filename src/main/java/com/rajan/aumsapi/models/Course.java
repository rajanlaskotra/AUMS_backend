package com.rajan.aumsapi.models;

import java.sql.Timestamp;

public class Course {

	private int courseID;
	private String courseName;
	private String courseDesc;
	private String courseSkills;
	private String coursePrerequisites;
	private String courseLocation;
	private Timestamp last_modified;
	
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public String getCourseSkills() {
		return courseSkills;
	}
	public void setCourseSkills(String courseSkills) {
		this.courseSkills = courseSkills;
	}
	public String getCoursePrerequisites() {
		return coursePrerequisites;
	}
	public void setCoursePrerequisites(String coursePrerequisites) {
		this.coursePrerequisites = coursePrerequisites;
	}
	public String getCourseLocation() {
		return courseLocation;
	}
	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}
	
	public Timestamp getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(Timestamp last_modified) {
		this.last_modified = last_modified;
	}
	
	
}
