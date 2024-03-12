package com.ty.ess.portal.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.ty.ess.portal.entity.User;
import com.ty.ess.portal.repository.UserRepository;

public class UserDao {

	@Autowired
	UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User findUserByUserId(int id) {
		return userRepository.findById(id).get();
	}

}
