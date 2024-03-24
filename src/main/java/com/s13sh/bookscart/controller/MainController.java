package com.s13sh.bookscart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.s13sh.bookscart.dto.LoginHelper;
import com.s13sh.bookscart.dto.User;
import com.s13sh.bookscart.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
	UserService userService;

	@Autowired
	User user;

	@Autowired
	LoginHelper loginHelper;

	@GetMapping({ "/" })
	public String loadMain(HttpSession httpSession) {
		return "main";
	}

	@PostMapping({ "/home" })
	public String loadMainPage() {
		return "main";
	}

	@GetMapping("/signin")
	public String loadSignin(ModelMap map) {
		map.put("loginHelper", loginHelper);
		return "signin";
	}

	@GetMapping("/signup")
	public String loadSignup(ModelMap map) {
		map.put("user", user);
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(@Valid User user, BindingResult result, ModelMap map, HttpSession session) {
		if (result.hasErrors())
			return "signup";
		else
			return userService.saveUser(user, result, map, session);
	}

	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam int id, @RequestParam int otp, HttpSession session, ModelMap map) {
		return userService.verifyOtp(id, otp, session, map);
	}

}