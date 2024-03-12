package com.ty.ess.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ty.ess.portal.dao.UserDao;
import com.ty.ess.portal.dto.ResponseStructure;
import com.ty.ess.portal.entity.User;
import com.ty.ess.portal.exception.ValidationException;
import com.ty.ess.portal.payload.UserDto;
import com.ty.ess.portal.util.UserType;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveEmployee(UserDto userDto, BindingResult result) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError error : result.getFieldErrors()) {
				message = message + error.getDefaultMessage() + "\n";
			}
			throw new ValidationException(message);
		}
		User user = new User();
		user.builder().name(userDto.getName()).email(userDto.getEmail()).password(userDto.getPassword())
				.userType(UserType.EMPLOYEE).build();

		user = userDao.saveUser(user);

		ResponseStructure<User> response = new ResponseStructure<User>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Success");
		response.setData(user);

		return new ResponseEntity<ResponseStructure<User>>(HttpStatus.CREATED);

	}
	
	public ResponseEntity<ResponseStructure<User>> saveManager(UserDto userDto, BindingResult result) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError error : result.getFieldErrors()) {
				message = message + error.getDefaultMessage() + "\n";
			}
			throw new ValidationException(message);
		}
		User user = new User();
		user.builder().name(userDto.getName()).email(userDto.getEmail()).password(userDto.getPassword())
				.userType(UserType.MANAGER).build();

		user = userDao.saveUser(user);

		ResponseStructure<User> response = new ResponseStructure<User>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Success");
		response.setData(user);

		return new ResponseEntity<ResponseStructure<User>>(HttpStatus.CREATED);

	}
	
}
