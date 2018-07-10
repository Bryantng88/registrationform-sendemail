package com.javaregistrationformsubmit.service;

import com.javaregistrationformsubmit.model.Mail;
import com.javaregistrationformsubmit.model.RegistrationInformation;

public interface EmailNotifyService {
	public void sendNotification(Mail mail, RegistrationInformation registrationInformation);
}
