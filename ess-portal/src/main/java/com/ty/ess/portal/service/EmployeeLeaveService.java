package com.ty.ess.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ty.ess.portal.dao.EmployeeLeaveDao;
import com.ty.ess.portal.dao.UserDao;
import com.ty.ess.portal.dto.ResponseStructure;
import com.ty.ess.portal.entity.EmployeeLeave;
import com.ty.ess.portal.entity.User;
import com.ty.ess.portal.exception.EmployeeLeaveRequestEmptyException;
import com.ty.ess.portal.exception.ValidationException;
import com.ty.ess.portal.payload.EmployeeLeaveDto;
import com.ty.ess.portal.util.LeaveStatus;

@Service
public class EmployeeLeaveService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private EmployeeLeaveDao employeeLeaveDao;

	// Apply leave Request
	public ResponseEntity<ResponseStructure<EmployeeLeave>> createLeaveRequest(int employeeId,
			EmployeeLeaveDto employeeLeaveRequest, BindingResult result) {
		if (result.hasErrors()) {
			String message = "";
			for (FieldError error : result.getFieldErrors()) {
				message = message + error.getDefaultMessage() + "\n";
			}
			throw new ValidationException(message);
		}
		EmployeeLeave employeeLeave = new EmployeeLeave();
		User user = userDao.findUserByUserId(employeeId);
		if (user != null) {
			employeeLeave.setLeaveDate(employeeLeave.getLeaveDate());
			employeeLeaveDao.createEmployeeLeaveRequest(employeeLeave);

			ResponseStructure<EmployeeLeave> response = new ResponseStructure<EmployeeLeave>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Success");
			response.setData(employeeLeave);
			return new ResponseEntity<ResponseStructure<EmployeeLeave>>(response, HttpStatus.CREATED);
		} else {
			throw new UsernameNotFoundException("Employee Not Found..!");
		}
	}

	// find leave list by EmployeeId
	public ResponseEntity<ResponseStructure<List<EmployeeLeave>>> findEmployeeLeaveByEmployeeId(int employeeId) {
		List<EmployeeLeave> employeeLeaves = employeeLeaveDao.findEmployeeById(employeeId);
		if (!employeeLeaves.isEmpty()) {
			ResponseStructure<List<EmployeeLeave>> response = new ResponseStructure<List<EmployeeLeave>>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Found");
			response.setData(employeeLeaves);

			return new ResponseEntity<ResponseStructure<List<EmployeeLeave>>>(response, HttpStatus.OK);
		} else {
			throw new EmployeeLeaveRequestEmptyException("Employee leave request is empty");
		}
	}
	
	public ResponseEntity<ResponseStructure<EmployeeLeave>> acceptLeave(int leaveId) {
		EmployeeLeave employeeLeave = this.employeeLeaveDao.findByLeaveId(leaveId);
		if(employeeLeave != null) {
			employeeLeave.setLeaveStatus(LeaveStatus.APPROVED);
			employeeLeaveDao.createEmployeeLeaveRequest(employeeLeave);
			ResponseStructure<EmployeeLeave> responseStructure = new ResponseStructure<EmployeeLeave>();
			responseStructure.builder().statusCode(HttpStatus.OK.value()).message("Approved").data(employeeLeave);
			
			return new ResponseEntity<ResponseStructure<EmployeeLeave>>(responseStructure, HttpStatus.OK);
			
		} else {
			throw new UsernameNotFoundException("Employee leave request not found");
		}
	}

	public ResponseEntity<ResponseStructure<EmployeeLeave>> rejectLeave(int leaveId) {
		EmployeeLeave employeeLeave = this.employeeLeaveDao.findByLeaveId(leaveId);
		if (employeeLeave != null) {
			employeeLeave.setLeaveStatus(LeaveStatus.REJECTED);
			employeeLeaveDao.createEmployeeLeaveRequest(employeeLeave);
			ResponseStructure<EmployeeLeave> responseStructure = new ResponseStructure<EmployeeLeave>();
			responseStructure.builder().statusCode(HttpStatus.OK.value()).message("Rejected").data(employeeLeave);
			
			return new ResponseEntity<ResponseStructure<EmployeeLeave>>(responseStructure, HttpStatus.OK);
		} else {
			throw new UsernameNotFoundException("Employee leave request not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<EmployeeLeave>>> getAllLeaveRequests() {
		List<EmployeeLeave> employeeLeaves = this.employeeLeaveDao.findAllLeaveRequests();
		if(!employeeLeaves.isEmpty()) {
			ResponseStructure<List<EmployeeLeave>> responseStructure = new ResponseStructure<List<EmployeeLeave>>();
			responseStructure.builder().statusCode(HttpStatus.OK.value()).message("Success").data(employeeLeaves).build();
			
			return new ResponseEntity<ResponseStructure<List<EmployeeLeave>>>(responseStructure, HttpStatus.OK);
		} else {
			throw new EmployeeLeaveRequestEmptyException("Employee leave request is empty");
		}
	}
}

