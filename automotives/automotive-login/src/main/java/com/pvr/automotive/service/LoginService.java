package com.pvr.automotive.service;

import com.pvr.automotive.entity.Login;
import com.pvr.automotive.response.LoginResponse;

public interface LoginService {
	
	public LoginResponse getProfile(Login request);
	public LoginResponse createProfile(Login request);

}
