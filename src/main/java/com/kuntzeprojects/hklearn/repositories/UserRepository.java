package com.kuntzeprojects.hklearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kuntzeprojects.hklearn.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	
	@Query("SELECT obj FROM User obj WHERE obj.verificationCode = :code")
	User findByVerificationCode(String code);
}
