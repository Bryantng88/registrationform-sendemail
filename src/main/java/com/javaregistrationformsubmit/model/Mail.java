package com.javaregistrationformsubmit.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Mail {
	
	@Value("${first.Name}")
	private String firstName;
	@Value("${last.Name}")
	private String lastName;
	@Value("${email.Address}")
	private String emailAddress;
	
	private String content;
	
	@Value("${email.Subject}")
	private String subject;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Override
	public String toString() {
		return "Mail [firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + "]";
	}


}