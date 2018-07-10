package com.javaregistrationformsubmit.service.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.javaregistrationformsubmit.model.Mail;
import com.javaregistrationformsubmit.model.RegistrationInformation;
import com.javaregistrationformsubmit.service.EmailNotifyService;

@Service
public class EmailNotifyServiceImp implements EmailNotifyService {
	private JavaMailSender javaMailSender;

	@Autowired
	public EmailNotifyServiceImp(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendNotification(Mail mail, RegistrationInformation registrationInformation) {

		// configuration

//		mail.setFirstName("Long");
//		mail.setLastName("dep trai");
//		mail.setEmailAddress("bryantng88@gmail.com");
//		mail.setSubject("alo");

		String content = "Gửi IT Support, \n" + registrationInformation.getDepartmentName()
				+ " mong muốn yêu cầu hỗ trợ nội dung như sau: \n" + registrationInformation.getAccountTypes() + " "
				+ registrationInformation.getFileName() + "\n"
				+ "mong IT Supprot hỗ trợ sớm để đảm bảo công việc đơn vị" + "\n \n" + "Kính gửi thủ trưởng đơn vị "
				+ registrationInformation.getDepartmentManager() + ", \n"
				+ "Thủ trưởng vui lòng xác nhận email này để bên IT Support có cơ sở thực hiện yêu cầu " + "\n"
				+ "Note: Email này được tạo ra từ web yêu cầu: ";

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);

			// set mail subject, content, etc
			helper.setSubject(mail.getSubject());
			helper.setText(content);
			helper.setTo(mail.getEmailAddress());
			helper.setFrom("bryantng88@gmail.com");

			// read file
			File file = new File("C:\\Users\\Public\\" + registrationInformation.getFileName());
			File image = new File("C:\\Users\\Public\\" + registrationInformation.getImageName());

			// try {
			// BufferedReader br = new BufferedReader(new FileReader(file));
			// System.out.println("Content: ");
			// String st;
			// while ((st = br.readLine()) != null){
			// System.out.println(st);
			// }
			// } catch (Exception e) {
			// // TODO: handle exception
			// }

			// add attachment
			helper.addAttachment(registrationInformation.getImageName(), image);
			helper.addAttachment(registrationInformation.getFileName(), file);

			// send
			javaMailSender.send(message);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// below for sending simple e-mail

		// System.out.println(content);
		//
		// SimpleMailMessage mail = new SimpleMailMessage();
		// mail.setTo(user.getEmailAddress());
		// mail.setFrom("bryantng88@gmail.com");
		// mail.setSubject("hello thay");
		// mail.setText(content);
		//
		//
		// javaMailSender.send(mail);
	}
}
