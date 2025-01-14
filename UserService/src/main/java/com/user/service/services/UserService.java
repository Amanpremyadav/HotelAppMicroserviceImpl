package com.user.service.services;

import java.util.List;
import java.util.Optional;

import com.user.service.entities.User;

public interface UserService {
	// user operations

	// create
	User saveUser(User user);

	// get all users
	List<User> getAllUser();

	// get single user of given userId
	Optional<User> getUser(String userId);
}
