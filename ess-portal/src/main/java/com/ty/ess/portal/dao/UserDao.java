package com.ty.ess.portal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ty.ess.portal.entity.User;
import com.ty.ess.portal.exception.UserNotFoundException;
import com.ty.ess.portal.repository.UserRepository;

public class UserDao {

	@Autowired
	UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User findUserByUserId(int id) {

		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new UserNotFoundException();
		}
	}

}
