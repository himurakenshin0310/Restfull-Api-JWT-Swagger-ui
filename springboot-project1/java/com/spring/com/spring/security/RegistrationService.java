package com.spring.security;

import com.spring.model.UserDto;

public interface RegistrationService {
	public void registraion(UserDto dto) throws Exception;
	
	public boolean checkEmailExsit(String email);
}
