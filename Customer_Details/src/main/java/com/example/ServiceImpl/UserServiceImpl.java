package com.example.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.User;
import com.example.JWT.JwtProvider;
import com.example.Repository.UserRepository;
import com.example.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> opt = userRepository.findById(userId);	
		if(opt.isPresent()) 
		{
			return opt.get();
		}
		throw new Exception("User not found with id "+ userId);		
	}

	@Override
	public User findUserByJwt(String jwt) throws Exception {
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		if(email == null) 
		{
			throw new Exception("provide valid jwt token...");
		}
		
		User user = userRepository.findByEmail(email);
		if(user == null) 
		{
			throw new Exception("user not found with email "+email);
		}
		return user;
	}
}

