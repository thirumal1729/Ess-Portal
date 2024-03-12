package com.ty.ess.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ess.portal.dto.ResponseStructure;
import com.ty.ess.portal.entity.EmployeeLeave;
import com.ty.ess.portal.payload.EmployeeLeaveDto;
import com.ty.ess.portal.service.EmployeeLeaveService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employeeLeave")
public class EmployeeLeaveController {
	@Autowired
	private EmployeeLeaveService employeeLeaveService;

	// create leave request
	@Operation(description = "To Apply Leave Request", summary = "Leave will be Applied")
	@ApiResponses(value = @ApiResponse(description = "OK", responseCode = "200"))
	@PostMapping("/{employeeId}")
	public ResponseEntity<ResponseStructure<EmployeeLeave>> createLeaveRequest(@PathVariable int employeeId,
			@Valid @RequestBody EmployeeLeaveDto employeeLeaveRequest, BindingResult result) {

		return employeeLeaveService.createLeaveRequest(employeeId, employeeLeaveRequest, result);
	}

	// find leave list by employee id
	@Operation(description = "Get Employee Leave List", summary = "Leave list are found")
	@ApiResponses(value = @ApiResponse(description = "OK", responseCode = "200"))
	@GetMapping("/{employeeId}")
	public ResponseEntity<ResponseStructure<List<EmployeeLeave>>> findLeaveListByEmployeeId(
			@PathVariable int employeeId) {
		return employeeLeaveService.findEmployeeLeaveByEmployeeId(employeeId);
	}

}
