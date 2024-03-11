package com.ty.ess.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ess.portal.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

}
