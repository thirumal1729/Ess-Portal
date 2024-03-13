package com.ty.ess.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ess.portal.dto.ResponseStructure;
import com.ty.ess.portal.entity.EmployeeLeave;
import com.ty.ess.portal.payload.EmployeeLeaveDto;
import com.ty.ess.portal.service.EmployeeLeaveService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employeeLeave")
@Tag(name = "Employee Leaves Management", description = "Employee Leaves Related")
public class EmployeeLeaveController {
	
	@Autowired
	private EmployeeLeaveService employeeLeaveService;

	// create leave request
	@Operation(description = "To Apply Leave Request", summary = "Leave will be Applied")
	@ApiResponses(value = {@ApiResponse(description = "CREATED", responseCode = "201"), @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content), @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content)})
	@PostMapping(value = "/{employeeId}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseStructure<EmployeeLeave>> createLeaveRequest(@PathVariable int employeeId,
			@Valid @RequestBody EmployeeLeaveDto employeeLeaveRequest, BindingResult result) {

		return employeeLeaveService.createLeaveRequest(employeeId, employeeLeaveRequest, result);
	}

	// find leave list by employee id
	@Operation(description = "Get Employee Leave List", summary = "Leave list are found")
	@ApiResponses(value = {@ApiResponse(description = "OK", responseCode = "200"), @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content), @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content)})
	@GetMapping(value = "/{employeeId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<ResponseStructure<List<EmployeeLeave>>> findLeaveListByEmployeeId(
			@PathVariable int employeeId) {
		return employeeLeaveService.findEmployeeLeaveByEmployeeId(employeeId);
	}
	
	@Operation(description = "To approve leave", summary = "manager will approve the leave")
	@ApiResponses(value = {@ApiResponse(description = "OK", responseCode = "200"), @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content), @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content)})
	@PutMapping(value = "/{leaveId}/accept", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<ResponseStructure<EmployeeLeave>> acceptLeave(@PathVariable int leaveId) {
		return this.employeeLeaveService.acceptLeave(leaveId);
	}
	
	@Operation(description = "To reject leave", summary = "manager will reject the leave")
	@ApiResponses(value = {@ApiResponse(description = "OK", responseCode = "200"), @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content), @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content)})
	@PutMapping(value = "/{leaveId}/reject", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<ResponseStructure<EmployeeLeave>> rejectingLeave(@PathVariable int leaveId) {
		return this.employeeLeaveService.rejectLeave(leaveId);
	}

	@Operation(description = "To get all leave request by manager", summary = "manager will get all leave requests")
	@ApiResponses(value = {@ApiResponse(description = "OK", responseCode = "200"), @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content), @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content) , @ApiResponse(description = "NO CONTENT", responseCode = "204", content = @Content)})
	@GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<ResponseStructure<List<EmployeeLeave>>> getAllLeaveRequests() {
		return this.employeeLeaveService.getAllLeaveRequests();
	}
	
}
