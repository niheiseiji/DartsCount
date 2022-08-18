package com.example.demo.app.inquiry;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InquiryForm {
	
	@NotNull
	@Size(min = 1, max = 20)
	private String name;
	
	@NotNull
	@Email()
	private String email;
	
	@NotNull
	@Size(min = 1, max = 200)
	private String contents;

//	public InquiryForm() {
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
