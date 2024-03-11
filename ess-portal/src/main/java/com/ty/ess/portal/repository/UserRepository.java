package com.ty.ess.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ess.portal.entity.Employee;

public interface UserRepository extends JpaRepository<Employee, Integer> {

}
