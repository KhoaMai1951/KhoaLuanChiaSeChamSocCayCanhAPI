package com.myclass.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 
import com.myclass.entity.User;
import com.myclass.service.UserService;

@RestController
@RequestMapping("/test/api")
public class TestController {
	@Autowired
	UserService userService;

	@GetMapping()
	List<User> all() {
		return this.userService.findAll();
	}
	
	@GetMapping("/string")
	String string() {
		return "aaa";
	}

//	// soft delete
//	@GetMapping(path = "/soft-delete")
//	void softDeleteUser(@RequestParam String id, HttpSession session) {  
//		int targetId = Integer.parseInt(id);
//		this.userService.softDelete(1, targetId); 
//	}
}
