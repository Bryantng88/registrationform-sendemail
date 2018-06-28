package com.javaregistrationformsubmit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.javaregistrationformsubmit.model.RegistrationDetail;
import com.javaregistrationformsubmit.model.User;
import com.javaregistrationformsubmit.service.NotificationService;




@RestController
public class RegistrationController {
	
	private Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	User user;

	
	@Autowired
	RegistrationDetail detailObject;
	
	@Autowired
	NotificationService notify;

	@RequestMapping(value="/getUser", method = RequestMethod.GET)
	public User getUser() {
		return user;
	}
	
    @RequestMapping(value="/detail", method = RequestMethod.POST)
    public RegistrationDetail submit(@RequestBody RegistrationDetail detail) {  	
    	return detail;
    }
    
    @RequestMapping(value="/userPost", method = RequestMethod.POST)
    public User getUserNameByPost(@RequestBody User user) {
    	return user;
    }
    
    @RequestMapping(value="registration-success", method = RequestMethod.POST)
    public RegistrationDetail registrationSuccess(@RequestBody RegistrationDetail detailObject) {
    	
    	
    	
    	//create user
    	User user = new User();
    	user.setFirstName("Long");
    	user.setLastName("dep trai");
    	user.setEmailAddress("bryantng88@gmail.com");
    	
    	//create detail content
    	
    	//send a notify 
    	try {
    		notify.sendNotification(user, detailObject);
    		System.out.println("success");
    	}catch(MailException e){
    		// catch error
    		logger.info("error sendign email: " + e.getMessage());
    	}
    	System.out.println(detailObject);
    	return detailObject;
    }
    
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public void getUserName() {
//    	User user = new User();
//    	user.setName("abc");
//    	System.out.println(user);
//    }
}
