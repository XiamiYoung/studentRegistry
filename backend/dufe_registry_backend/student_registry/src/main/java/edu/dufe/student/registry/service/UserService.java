package edu.dufe.student.registry.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
	public UserDetails findByUserNameAndPassword(String username, String password) throws UsernameNotFoundException;
}
