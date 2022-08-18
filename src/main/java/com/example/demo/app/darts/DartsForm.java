package com.example.demo.app.darts;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DartsForm {
	@NotNull
	@Min(0) @Max(1440)
	private int totalScore;
	
//	@NotNull
//	@Email()
//	private String email;
//	
//	@NotNull
//	@Size(min = 1, max = 200)
//	private String contents;

	public DartsForm() {
	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getContents() {
//		return contents;
//	}
//	public void setContents(String contents) {
//		this.contents = contents;
//	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
}
