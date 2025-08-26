package com.pvr.automotive.controller;

import java.io.ObjectInputFilter.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvr.automotive.entity.Login;
import com.pvr.automotive.entity.Signin;
import com.pvr.automotive.response.LoginResponse;
import com.pvr.automotive.serviceImpl.LoginServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	
	@Autowired
	private LoginServiceImpl loginServiceImpl;
	
	@PostMapping("/getProfile")
	ResponseEntity<?> getProfile(@RequestBody Login request){
		log.info("Request for login : {}", request.getEmail());
		try {
			LoginResponse response = loginServiceImpl.getProfile(request);
			return ResponseEntity.ok().body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}	
	}
	
	@PostMapping("/createProfile")
	ResponseEntity<?> createProfile(@RequestBody Login request){
		log.info("Creating a new profile : {}", request.getEmail());
		try {
			LoginResponse response = loginServiceImpl.createProfile(request);
			return ResponseEntity.ok().body(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
