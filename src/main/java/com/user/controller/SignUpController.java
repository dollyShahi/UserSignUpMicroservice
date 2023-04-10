package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.Model;
import com.user.jpa.Repository;
import com.user.jwt.JwtUtelis;

@CrossOrigin("http://localhost:4200")
@RestController
public class SignUpController {
	
	@Autowired
	Repository  repository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtelis util;
	
	
	@PostMapping ("/registration")
	
	public String addmodel(@RequestBody Model model) {
	
	if(repository.findById(model.getEmail_id()).isEmpty()) {
	
	try {
	BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
	String encodedPassword=bCryptPasswordEncoder.encode(model.getPassword());
	model.setPassword(encodedPassword);
	repository.save(model);
	
	return "Added";
	}catch(Exception e) { 
	return	"error";
	}
	}
	else {
		return "duplicate";
	}

}

}
