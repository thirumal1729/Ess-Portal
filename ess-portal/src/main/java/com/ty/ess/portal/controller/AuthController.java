package com.ty.ess.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "User Login", description = "User login related")
public class AuthController {
	
	@Autowired
	UserService userService;

	@Operation(description = "To Login user", summary = "user will login")
	@ApiResponses(value = {@ApiResponse(description = "OK", responseCode = "200"), @ApiResponse(description = "Invalid username or password", responseCode = "403", content = @Content)})
	@PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<JwtResponse>> login(@RequestBody JwtRequest request) {
		return this.userService.login(request);
	}

	

}
