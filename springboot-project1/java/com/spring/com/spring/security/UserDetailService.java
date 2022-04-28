package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.entity.User;
import com.spring.repository.UserRepository;
@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByemail(username);
		if (user == null)
			throw new UsernameNotFoundException("not found username:" + username);
		else
			return new UserCustomDetail(user);
	}

}
