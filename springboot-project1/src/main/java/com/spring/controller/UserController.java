package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.UserDto;
import com.spring.security.JsonTokenProvider;
import com.spring.security.RegistrationService;
import com.spring.security.UserCustomDetail;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JsonTokenProvider jwtToken;

	@Autowired
	RegistrationService signUpService;

	@PostMapping("/login.html")
	public ResponseEntity<responObject> login(@RequestBody UserDto user) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Map<String, String> map = new HashMap<String, String>();
		map.put("access_token", jwtToken.generateToken((UserCustomDetail) authentication.getPrincipal()));
		return ResponseEntity.status(HttpStatus.OK).body(new responObject("200", "succsess!", map));
	}

	@PostMapping("/sign-up.html")
	public ResponseEntity<responObject> signup(@RequestBody UserDto user) throws Exception {
		System.out.println(user.getPassword());
		signUpService.registraion(user);
		return ResponseEntity.status(HttpStatus.OK).body(new responObject("200", "ok", null));
	}
}
