package com.myclass.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Override
	@Query(value = 
			"SELECT * FROM users u ", nativeQuery = true)
	public List<User> findAll();
	
	
}
