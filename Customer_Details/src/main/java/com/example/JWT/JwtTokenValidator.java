package com.example.JWT;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
		
		if(jwt!=null) 
		{
			jwt = jwt.substring(7);
			try 
			{
				SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
				Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				String email = String.valueOf(claims.get("email"));
				Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, null);				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} 
			catch (Exception e) {
//			    logger.error("Exception while parsing JWT token", e);
			    throw new BadCredentialsException("Invalid token....");
			}
		}
		filterChain.doFilter(request, response);
	}

}