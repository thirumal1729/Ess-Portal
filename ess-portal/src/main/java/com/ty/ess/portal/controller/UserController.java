package com.ty.ess.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User related")
public class UserController {

	@Autowired
	UserService userService;

	@Operation(description = "To save employee info", summary = "employee will be saved")
	@ApiResponses(value = @ApiResponse(description = "Employee Created", responseCode = "201"))
	@PostMapping(value = "/employee", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<User>> saveEmployees(@RequestBody UserDto userDto, BindingResult result) {

		return userService.saveEmployee(userDto, result);

	}

	@Operation(description = "To save manager info", summary = "manager will be saved")
	@ApiResponses(value = @ApiResponse(description = "Manager Created", responseCode = "201"))
	@PostMapping(value = "/manager", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ResponseStructure<User>> saveManager(@Valid @RequestBody UserDto userDto, BindingResult result) {

		return userService.saveManager(userDto, result);

	}
}
