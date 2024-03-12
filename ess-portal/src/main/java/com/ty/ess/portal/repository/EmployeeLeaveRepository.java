package com.ty.ess.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ess.portal.entity.EmployeeLeave;

public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Integer> {
	
	//custom method for find all leaves of particular employee
	List<EmployeeLeave> findByUserUserId(int userId);
}
