package com.ty.ess.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ess.portal.dto.ResponseStructure;
import com.ty.ess.portal.payload.JwtRequest;
import com.ty.ess.portal.payload.JwtResponse;
import com.ty.ess.portal.service.UserService;
import com.ty.ess.portal.util.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<JwtResponse>> login(@RequestBody JwtRequest request) {
		return this.userService.login(request);
	}

	

}
