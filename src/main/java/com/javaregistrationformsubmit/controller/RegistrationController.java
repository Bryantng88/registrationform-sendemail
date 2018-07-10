package com.javaregistrationformsubmit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.javaregistrationformsubmit.model.Mail;
import com.javaregistrationformsubmit.model.RegistrationInformation;
import com.javaregistrationformsubmit.service.EmailNotifyService;
import com.javaregistrationformsubmit.service.FileConvertService;

@RestController
@RequestMapping("/registration/")
public class RegistrationController {

	private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	private static Gson gson = new Gson();

	@Autowired
	private Mail mail;

	@Autowired
	private EmailNotifyService notify;

	@Autowired
	private FileConvertService fileConvertService;

	@RequestMapping(value = "autocomplete", method = RequestMethod.GET)
	public String autocomplete() {	
		return "abc";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public Boolean create(@RequestBody RegistrationInformation registrationInformation) {

		// convert base64 to file
		fileConvertService.convert(registrationInformation.getFiles(), registrationInformation.getFileName());
		fileConvertService.convert(registrationInformation.getImages(), registrationInformation.getImageName());
	
		System.out.println(gson.toJson(registrationInformation));
		
		// execute send-email
		notify.sendNotification(mail, registrationInformation);
		System.out.println("done");
		return true;

	}

}
