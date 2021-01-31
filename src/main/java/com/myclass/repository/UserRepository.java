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
			"SELECT * FROM users u  " + 
			"WHERE u.is_deleted = 0", nativeQuery = true)
	public List<User> findAll();
	
	@Query(value = 
			"SELECT * FROM users u  " + 
			"WHERE u.is_deleted = 1", nativeQuery = true)
	public List<User> findAllDeleted();
	
	@Query(value = 
			"SELECT * FROM users u  " + 
			"WHERE u.is_deleted = 1", nativeQuery = true)
	public List<User> deleteById();
	
	@Query(value = "SELECT * FROM users u WHERE u.is_deleted = 0 AND u.email = :email AND u.password = :password", nativeQuery = true)
	public User findUserIdByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	@Query(value = "SELECT * FROM users u "
			+ "WHERE u.is_deleted = 0 "
			+ "AND u.email = :email AND u.password = :password AND u.role_id = 1", nativeQuery = true)
	public User findAdminIdByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	@Query(value = 
			"SELECT u.* FROM posts p INNER JOIN users u " + 
			"WHERE p.id = :postId " + 
			"AND u.id = p.user_id", nativeQuery = true)
	public User findUserByPostId(@Param("postId") String postId);
	
	@Query(value = 
			"SELECT * FROM users u  " + 
			"WHERE u.role_id = 2 AND u.is_deleted = 0", nativeQuery = true)
	public List<User> findAllUsers();
	
	@Query(value = 
			"SELECT * FROM users u  " + 
			"WHERE u.role_id = 2 AND u.is_deleted = 1", nativeQuery = true)
	public List<User> findAllDeletedUsers();
	
	@Query(value = 
			"SELECT * FROM users u  " + 
			"WHERE u.role_id = 1 AND u.is_deleted = 0", nativeQuery = true)
	public List<User> findAllAdmins();
	
	@Query(value = 
			"SELECT * FROM users u  " + 
			"WHERE u.role_id = 1 AND u.is_deleted = 1", nativeQuery = true)
	public List<User> findAllDeletedAdmins();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users u SET u.is_deleted = 1 WHERE u.id = :id", nativeQuery = true)
	void softDelete(@Param("id") int id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE USERS u SET u.is_deleted = 0 WHERE u.id = :id", nativeQuery = true)
	void restore(@Param("id") int id);
	
	@Query(value = "SELECT u.email FROM users u WHERE u.email = :email", nativeQuery = true)
	String findEmail(@Param("email") String email);
	
	@Query(value = "SELECT * FROM users u WHERE u.email = :email", nativeQuery = true)
	User findUserByEmail(@Param("email") String email);
	
	@Query(value = "SELECT u.id FROM users u WHERE u.email = :email", nativeQuery = true)
	int findIdByEmail(@Param("email") String email);
	
	
}
