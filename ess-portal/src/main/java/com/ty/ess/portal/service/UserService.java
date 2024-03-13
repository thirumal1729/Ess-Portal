package com.ty.ess.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ty.ess.portal.dao.UserDao;
import com.ty.ess.portal.dto.ResponseStructure;
import com.ty.ess.portal.entity.User;
import com.ty.ess.portal.exception.ValidationException;
import com.ty.ess.portal.payload.JwtRequest;
import com.ty.ess.portal.payload.JwtResponse;
import com.ty.ess.portal.payload.UserDto;
import com.ty.ess.portal.util.JwtHelper;
import com.ty.ess.portal.util.UserType;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtHelper helper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// performs validation and save employee operations
	public ResponseEntity<ResponseStructure<User>> saveEmployee(UserDto userDto, BindingResult result) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError error : result.getFieldErrors()) {
				message = message + error.getDefaultMessage() + "\n";
			}
			throw new ValidationException(message);
		}
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setUserType(UserType.EMPLOYEE);

		user = userDao.saveUser(user);

		ResponseStructure<User> response = new ResponseStructure<User>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Success");
		response.setData(user);

		return new ResponseEntity<ResponseStructure<User>>(response, HttpStatus.CREATED);

	}

	// performs validation and save manager operations
	public ResponseEntity<ResponseStructure<User>> saveManager(UserDto userDto, BindingResult result) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError error : result.getFieldErrors()) {
				message = message + error.getDefaultMessage() + "\n";
			}
			throw new ValidationException(message);
		}
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setUserType(UserType.MANAGER);

		user = userDao.saveUser(user);

		ResponseStructure<User> response = new ResponseStructure<User>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Success");
		response.setData(user);

		return new ResponseEntity<ResponseStructure<User>>(response, HttpStatus.CREATED);

	}

	// user login operations
	public ResponseEntity<ResponseStructure<JwtResponse>> login(JwtRequest request) {

		doAuthenticate(request.getEmail(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);

		JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();

		ResponseStructure<JwtResponse> responseStructure = new ResponseStructure<JwtResponse>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Logged in successfullu...!");
		responseStructure.setData(response);

		return new ResponseEntity<ResponseStructure<JwtResponse>>(responseStructure, HttpStatus.OK);

	}

	// authenticate details present in database
	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authenticationManager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

}
