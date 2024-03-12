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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class EmployeeController {

	@Autowired
	UserService userService;

	@Operation(description = "To save employee info", summary = "employee will be saved")
	@ApiResponses(value = @ApiResponse(description = "Employee Created", responseCode = "201"))
	@PostMapping("/employee")
	public ResponseEntity<ResponseStructure<User>> saveEmployees(@RequestBody UserDto userDto, BindingResult result) {

		return userService.saveEmployee(userDto, result);

	}

	@Operation(description = "To save manager info", summary = "manager will be saved")
	@ApiResponses(value = @ApiResponse(description = "Manager Created", responseCode = "201"))
	@PostMapping("/manager")
	public ResponseEntity<ResponseStructure<User>> saveManager(@Valid @RequestBody UserDto userDto, BindingResult result) {

		return userService.saveManager(userDto, result);

	}
}
