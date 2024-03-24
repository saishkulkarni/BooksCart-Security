package com.s13sh.bookscart.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.s13sh.bookscart.dao.UserDao;
import com.s13sh.bookscart.dto.User;
import com.s13sh.bookscart.helper.MailHelper;
import com.s13sh.bookscart.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	MailHelper mailHelper;

	@Override
	public String saveUser(User user, BindingResult result, ModelMap map, HttpSession session) {
		if (userDao.checkEmail(user.getEmail()))
			result.rejectValue("email", "error.email", "* Email Already Exists");
		if (userDao.checkMobile(user.getMobile()))
			result.rejectValue("mobile", "error.mobile", "* Mobile Already Exists");
		if (userDao.checkUserName(user.getUsername()))
			result.rejectValue("username", "error.username", "* Username Already Exists, Choose different one");
		if (result.hasErrors())
			return "signup";
		else {
			user.setRole("user");
			user.setPassword(encoder.encode(user.getPassword()));
			user.setOtp(new Random().nextInt(100000, 999999));
			userDao.save(user);

			if (mailHelper.send(user)) {
				session.setAttribute("successMessage", "Otp Sent Success");
				map.put("id", user.getId());
				return "enter-otp";
			} else {
				return "redirect:/";
			}
		}
	}

	@Override
	public String verifyOtp(int id, int otp, HttpSession session, ModelMap map) {
		User user = userDao.findById(id);
		if (user.getOtp() == otp) {
			session.setAttribute("successMessage", "Account created Success");
			user.setVerified(true);
			userDao.save(user);
			return "redirect:/signin";
		} else {
			session.setAttribute("failMessage", "Invalid OTP Try Again");
			map.put("id", id);
			return "enter-otp";
		}
	}

}
