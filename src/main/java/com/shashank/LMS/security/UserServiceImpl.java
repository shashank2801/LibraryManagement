package com.shashank.LMS.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("Username invalid");
		else {
			UserDetails userDetails = org.springframework.security.core.userdetails.User
					.builder()
					.username(user.getUsername())
					.password(user.getPassword())
					.roles(user.getRoles().toArray(new String[0]))
					.build();
			
			return userDetails;
		}
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	
	public void saveUserAsStudent(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("STUDENT"));
		userRepository.save(user);			
	}
	
	
	public void saveUserAsAdmin(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("ADMIN"));
		userRepository.save(user);	
		
	}

	public void saveUserAsLibrarian(User user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("LIBRARIAN"));
		userRepository.save(user);	
		
	}
}
