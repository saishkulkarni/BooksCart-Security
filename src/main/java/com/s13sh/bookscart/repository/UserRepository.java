package com.s13sh.bookscart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s13sh.bookscart.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByMobile(long mobile);

}
