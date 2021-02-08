package com.myclass.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.constant.PathConstant;
import com.myclass.entity.User;
import com.myclass.service.TestService;
import com.myclass.service.UserService;

@RestController
@RequestMapping("/test/api")
public class TestController {
	@Autowired
	UserService userService;

	@Autowired
	TestService testService;

	@Autowired
	ServletContext servletContext;

	@GetMapping()
	List<User> all() {
		return this.userService.findAll();
	}

	@GetMapping("/string")
	String string() {
		return "aaa";
	}

	// test upload image and show the image
	@PostMapping(
			path = "/upload", 
			consumes = { "multipart/form-data" }, 
			produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] postAddNews(@RequestPart("file") MultipartFile file) throws IOException {

		String imageName = this.testService.uploadImage(file); //System.out.println(imageName); System.exit(0); 
		
		InputStream in = getClass().getResourceAsStream(PathConstant.DATABASE_IMAGE_DIRECTORY + imageName);
		//System.out.println(PathConstant.DATABASE_IMAGE_DIRECTORY + imageName); System.exit(0);
		//InputStream in = getClass().getResourceAsStream("/static/images/post/97233e56-18c3-4d6d-9eaa-3ca78b5c198c.jpeg");

		
		return IOUtils.toByteArray(in);
	}

//	// test get image  
//	@GetMapping(value = "/get-image", produces = MediaType.IMAGE_JPEG_VALUE)
//	public @ResponseBody byte[] getImageWithMediaType() throws IOException {
//		InputStream in = getClass().getResourceAsStream(PathConstant.DATABASE_IMAGE_DIRECTORY + "b2f9bb26-3f88-44d3-9bca-195cb1533856.jpeg");
//		return IOUtils.toByteArray(in);
//	}


}
