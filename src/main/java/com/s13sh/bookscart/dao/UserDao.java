package com.s13sh.bookscart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.s13sh.bookscart.dto.User;
import com.s13sh.bookscart.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public boolean checkUserName(String username) {
		return userRepository.existsByUsername(username);
	}

	public boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public boolean checkMobile(long mobile) {
		return userRepository.existsByMobile(mobile);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public User findById(int id) {
		return userRepository.findById(id).orElseThrow();
	}

}
