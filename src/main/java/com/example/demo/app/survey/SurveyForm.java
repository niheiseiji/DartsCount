package com.example.demo.app.survey;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SurveyForm {

	@NotNull
	@Min(0) @Max(150)
	private int age;

	@NotNull
	@Min(0) @Max(1)
	private int gender;
	
//	@NotNull
//	@Min(1) @Max(5)
//	private int satisfaction;
//	
	@NotNull
	@Size(min = 1, max = 200)
	private String comment;

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
//	public int getSatisfaction() {
//		return satisfaction;
//	}
//	public void setSatisfaction(int satisfaction) {
//		this.satisfaction = satisfaction;
//	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
