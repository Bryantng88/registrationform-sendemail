package com.javaregistrationformsubmit.model;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class RegistrationDetail {
	private String departmentName;
	private String departmentManager;
	private String account[];
	private String attachmentName;


	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentManager() {
		return departmentManager;
	}

	public void setDepartmentManager(String departmentManager) {
		this.departmentManager = departmentManager;
	}

	public String[] getAccount() {
		return account;
	}

	public void setAccount(String[] account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "RegistrationDetail [departmentName=" + departmentName + ", departmentManager=" + departmentManager
				+ ", account=" + Arrays.toString(account) + ", attachmentName=" + attachmentName + "]";
	}




}
