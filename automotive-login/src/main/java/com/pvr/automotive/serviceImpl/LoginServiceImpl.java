package com.pvr.automotive.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvr.automotive.entity.Login;
import com.pvr.automotive.repository.LoginRepository;
import com.pvr.automotive.response.LoginResponse;
import com.pvr.automotive.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public LoginResponse getProfile(Login request) {
		// TODO Auto-generated method stub
		log.info("Receving response from DB : {}", request.getEmail());
		LoginResponse loginResponse = new LoginResponse();
		Optional<Login> dbResponse = loginRepository.findByEmail(request.getEmail());
		Login login = dbResponse.get();
		if(login != null && !login.toString().trim().isEmpty() 
				&& request.getPassword().equals(login.getPassword())) {
			loginResponse.setEmail(login.getEmail());
			loginResponse.setMessage("login successfull");
			log.info("Response of DB: {}", login.getEmail());
			return loginResponse;
		}else {
			loginResponse.setEmail(request.getEmail());
			loginResponse.setMessage("login failed");
			log.info("login failed {}", login.getEmail());
			return loginResponse;
		}
		
	}

	@Override
	public LoginResponse createProfile(Login request) {
	    log.info("Creating profile: {}", request.getEmail());
	    LoginResponse response = new LoginResponse();

	    Optional<Login> dbResponse = loginRepository.findByEmail(request.getEmail());

	    if(dbResponse.isPresent()) {
	        // User already exists
	        Login existingUser = dbResponse.get();
	        response.setEmail(existingUser.getEmail());
	        response.setMessage("User already present...");
	        log.info("User already exists: {}", existingUser.getEmail());
	        return response;
	    }

	    // Create new user
	    Login createUser = new Login();
	    createUser.setFirstName(request.getFirstName());
	    createUser.setLastName(request.getLastName());
	    createUser.setEmail(request.getEmail());
	    createUser.setPassword(request.getPassword()); // TODO: hash password
	    loginRepository.save(createUser);

	    response.setEmail(createUser.getEmail());
	    response.setMessage("User created successfully");
	    log.info("New user saved: {}", createUser.getEmail());
	    return response;
	}

	@Override
	public LoginResponse updateProfile(Login request) {
		log.info("updating profile: {}", request.getEmail());
		LoginResponse loginResponse = new LoginResponse();
		Optional<Login> dbResponse = loginRepository.findByEmail(request.getEmail());
		if(dbResponse.isPresent()) {
			Login existingUser = dbResponse.get();
			existingUser.setEmail(request.getEmail());
			existingUser.setFirstName(request.getFirstName());
			existingUser.setLastName(request.getLastName());
			existingUser.setPassword(request.getPassword());
			loginRepository.save(existingUser);
			loginResponse.setMessage("profile updated successfully.");
			log.info("Profile updated successfully for {}", request.getEmail());
			return loginResponse;
	    } else {
	        log.warn("No user found with email: {}", request.getEmail());
	        throw new RuntimeException("User not found with email: " + request.getEmail());
	    }
	}



}
