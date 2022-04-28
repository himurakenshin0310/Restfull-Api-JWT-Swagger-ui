package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.entity.User;
import com.spring.model.UserDto;
import com.spring.repository.RoleRepository;
import com.spring.repository.UserRepository;

@Service
public class RegistrationServiceImp implements RegistrationService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	PasswordEncoder passwordEncode;

	@Autowired
	RoleRepository roleRepo;

	@Override
	public void registraion(UserDto dto) throws Exception {
		if (checkEmailExsit(dto.getUsername()))
			throw new Exception("username already taken");
		else {
			User user = new User();
			user.setEmail(dto.getUsername());
			user.setPassword(passwordEncode.encode(dto.getPassword()));
			user.setRole(roleRepo.findById(1l).get());
			userRepo.save(user);
		}
	}

	@Override
	public boolean checkEmailExsit(String email) {
		return userRepo.findByemail(email) != null;
	}

}
