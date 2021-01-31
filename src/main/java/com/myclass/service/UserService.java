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

	public List<User> findAllDeleted() {
		return (List<User>) userRepository.findAllDeleted();
	}

	public User findById(int id) {
		return userRepository.findById(id).get();
	}

	public int findUserByEmailAndPassword(User user) {
		String email = user.getEmail();
		String password = user.getPassword();

		User userTemp = this.userRepository.findUserIdByEmailAndPassword(email, password);
		if (userTemp != null) {
			return userTemp.getId();
		}
		return -1;
	}

	public User findUserByEmail(User user) {
		return this.userRepository.findUserByEmail(user.getEmail());
	}

	public boolean verifyPassword(User user) {
		// find user in db
		User userDB = this.findUserByEmail(user);
		// if user exists
		if (userDB != null) {
			// compare hashed input password with database hashed password
			BCrypt.Result result = BCrypt.verifyer().verify(user.getPassword().toCharArray(), userDB.getPassword());
			// return password correct or not
			return result.verified;
		}
		// user not exists
		return false;
	}

	public int findAdminByEmailAndPassword(User user) {
		String email = user.getEmail();
		String password = user.getPassword();

		User userTemp = this.userRepository.findAdminIdByEmailAndPassword(email, password);
		if (userTemp != null) {
			return userTemp.getId();
		}
		return -1;
	}

	public boolean softDelete(int currentId, int targetId) {
		// if not delete admin himself
		if (currentId != targetId) {
			userRepository.softDelete(targetId);
			return true;
		}
		return false;
	}

	
}
