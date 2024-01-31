package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.User;
import com.example.JWT.JwtProvider;
import com.example.Repository.UserRepository;
import com.example.Request.LoginRequest;
import com.example.Response.AuthRespose;
import com.example.Service.CustomeUserDetailsService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomeUserDetailsService customeUserDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

	
    @PostMapping("/signup")
    public AuthRespose createUser(@RequestBody User user) throws Exception {
	
		String email =user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		
		User isExistEmail = userRepository.findByEmail(email);
		if(isExistEmail!=null) 
		{
			throw new Exception("Email is Already used with another account");
		}
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFullName(fullName);
		
		User savedUser = userRepository.save(createdUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthRespose res= new AuthRespose();
		res.setJwt(token);
		res.setMessage("SignUp success");
		
		return res;
		
//		try {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String token = jwtProvider.generateToken(authentication);
//
//            AuthRespose res = new AuthRespose();
//            res.setJwt(token);
//            res.setMessage("SignUp success");
//
//            return res;
//        } catch (Exception e) {
//            throw new Exception("Error during signup", e);
//        }

	}
	
	
    @PostMapping("/signin")
    public AuthRespose signinHandler(@RequestBody LoginRequest loginRequest) {
        
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		
		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthRespose res= new AuthRespose();
		res.setJwt(token);
		res.setMessage("SignIn success");
		
		return res;
		
//		try {
//            Authentication authentication = authenticate(username, password);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String token = jwtProvider.generateToken(authentication);
//
//            AuthRespose res = new AuthRespose();
//            res.setJwt(token);
//            res.setMessage("SignIn success");
//
//            return res;
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException("Invalid credentials", e);
//        }
		
	}

	private Authentication authenticate(String username, String password) {
		
		UserDetails userDetails = customeUserDetailsService.loadUserByUsername(username);
		
		if(userDetails == null) {
			throw new BadCredentialsException("user not found");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
    
//    private Authentication authenticate(String username, String password) {
//        UserDetails userDetails = customeUserDetailsService.loadUserByUsername(username);
//
//        if (userDetails == null) {
//            throw new BadCredentialsException("User not found");
//        }
//
//        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//            throw new BadCredentialsException("Invalid password");
//        }
//
//        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//    }
}

