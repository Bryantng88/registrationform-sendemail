package com.javaregistrationformsubmit.service.imp;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaregistrationformsubmit.model.RegistrationInformation;
import com.javaregistrationformsubmit.service.FileConvertService;

@Service
public class ConvertFileImp implements FileConvertService {

	@Autowired
	RegistrationInformation registrationInformation;

	public void convert(String files, String fileName) {
		// ClassLoader classLoader = getClass().getClassLoader();
		// File file = new
		// File(classLoader.getResource("attachment/"+registrationInformation.getFileName()).getFile());
		try {
			byte fileArr[] = Base64.decodeBase64(files);
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Public\\" + fileName);
			fos.write(fileArr);
			fos.close();
		} catch (IOException e) {
			
		}

	}

}
