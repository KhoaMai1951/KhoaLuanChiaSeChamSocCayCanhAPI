package com.myclass.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.constant.PathConstant;

@Service
public class TestService {

	// upload image and return the image name
	public String uploadImage(MultipartFile file) throws IOException {
		// image handle
		String path = FileSystems.getDefault().getPath("").toAbsolutePath().toString();

		String fileName = UUID.randomUUID().toString() + ".jpeg";
		InputStream is = file.getInputStream();

		Files.copy(is, Paths.get(path + PathConstant.IMAGE_DIRECTORY + fileName), 
				StandardCopyOption.REPLACE_EXISTING);
		
		// return image name
		return fileName;
	}
	
	
}
