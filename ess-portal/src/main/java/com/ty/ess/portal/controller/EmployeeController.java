package com.ty.ess.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ess.portal.dto.ResponseStructure;
import com.ty.ess.portal.entity.User;
import com.ty.ess.portal.payload.UserDto;
import com.ty.ess.portal.service.UserService;

@RestController
@RequestMapping("/users")
public class EmployeeController {

	@Autowired
	UserService userService;

	@PostMapping("/employee")
	public ResponseEntity<ResponseStructure<User>> saveEmployees(@RequestBody UserDto userDto, BindingResult result) {

		return userService.saveEmployee(userDto, result);

	}

	@PostMapping("/manager")
	public ResponseEntity<ResponseStructure<User>> saveManager(@RequestBody UserDto userDto, BindingResult result) {

		return userService.saveManager(userDto, result);

	}
}
