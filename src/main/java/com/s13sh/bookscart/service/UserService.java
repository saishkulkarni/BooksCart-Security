package com.s13sh.bookscart.service;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.s13sh.bookscart.dto.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	public String saveUser(User user, BindingResult result, ModelMap map, HttpSession session);

	public String verifyOtp(int id, int otp, HttpSession session, ModelMap map);
}