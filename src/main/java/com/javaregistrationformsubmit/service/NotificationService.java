package com.javaregistrationformsubmit.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.javaregistrationformsubmit.model.RegistrationDetail;
import com.javaregistrationformsubmit.model.User;

@Service
public class NotificationService {
	private JavaMailSender javaMailSender;
	

	
	@Autowired
	RegistrationDetail registrationDetail;
	
	@Autowired
	User user;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}
	
	public void sendNotification(User user, RegistrationDetail registrationDetail) throws MailException {
		registrationDetail.setAttachmentName("danh sach nhan vien");
		//send email
		System.out.println(registrationDetail.getDepartmentManager());
		String content = "Gui IT support, \n"
						+ registrationDetail.getDepartmentName() + " mong muon yeu cau ho tro noi dung nhu sau: \n"
						+ Arrays.toString(registrationDetail.getAccount()) + " " + registrationDetail.getAttachmentName() +"\n"
						+ "mong IT support ho tro som de bao dam cong viec don vi" 
						+"\n \n"
						+"Kinh gui thu truong don vi " + registrationDetail.getDepartmentManager() +", \n"
						+"Thu truong vui long xac nhan email nay de ben IT support co co so thuc hien yeu cau "
						+"Note: Email nay duoc tao ra tu web tao yeu cau";
						
						
						
		System.out.println(content);
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmailAddress());
		mail.setFrom("bryantng88@gmail.com");
		mail.setSubject("hello thay");
		mail.setText(content);
		
		
		javaMailSender.send(mail);
	}
}
