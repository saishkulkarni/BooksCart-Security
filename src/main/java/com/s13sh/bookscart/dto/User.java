package com.s13sh.bookscart.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Component
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 3, max = 10, message = "* Enter between 3-10 charecters")
	private String name;
	@Size(min = 3, max = 20, message = "* Enter between 3-20 charecters")
	private String username;
	@DecimalMin(value = "6000000000", message = "* Enter proper Mobile Number")
	@DecimalMax(value = "9999999999", message = "* Enter proper Mobile Number")
	private long mobile;
	@NotEmpty(message = "* This is Required field")
	@Email(message = "* Enter Proper Email")
	private String email;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}$", message = "* Password must contain one digit from 1 to 9, one lowercase letter, one uppercase letter, one special character, no space, and it must be 8-16 characters long.")
	private String password;
	private String role;
	@NotEmpty(message = "* This is Required field")
	private String gender;
	private int otp;
	private boolean verified;
}