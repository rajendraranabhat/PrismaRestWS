package com.prisma.pojo;

import java.util.ArrayList;

public class DoctorRegistration {

	String username;
	ArrayList<String> questions;
	String fullName;
	String password;
	String gender;
	String age;
	String currentRoles;
	String speciality;
	String experience;	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<String> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<String> questions) {
		this.questions = questions;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCurrentRoles() {
		return currentRoles;
	}

	public void setCurrentRoles(String currentRoles) {
		this.currentRoles = currentRoles;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	@Override
	public String toString() {
		return "[username:"+username+" fullName:"+fullName+" password:"+password+" gender:"+gender+
				" age:"+age+" currentRoles:"+currentRoles+" speciality:"+speciality+" experience:"+
				experience+" questions:"+questions.toString()+"]";
	}
}


