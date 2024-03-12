package com.ty.ess.portal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ess.portal.entity.EmployeeLeave;
import com.ty.ess.portal.repository.EmployeeLeaveRepository;

@Repository
public class EmployeeLeaveDao {

	@Autowired
	private EmployeeLeaveRepository employeeRepository;

	// create a leave request
	public EmployeeLeave createEmployeeLeaveRequest(EmployeeLeave employeeLeave) {
		return employeeRepository.save(employeeLeave);
	}

	// find list of employee leave by id
	public List<EmployeeLeave> findEmployeeById(int employeeId) {
		return employeeRepository.findByUserUserId(employeeId);
	}

	// find employee leave by leave id
	public EmployeeLeave findByLeaveId(int leaveId) {
		Optional<EmployeeLeave> optional = employeeRepository.findById(leaveId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public List<EmployeeLeave> findAllLeaveRequests() {
		return this.employeeRepository.findAll();
	}
}
