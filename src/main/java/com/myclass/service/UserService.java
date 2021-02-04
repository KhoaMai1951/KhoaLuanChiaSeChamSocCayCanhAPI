package com.myclass.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.myclass.entity.User; 
import com.myclass.repository.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository; 

	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	

	
}
