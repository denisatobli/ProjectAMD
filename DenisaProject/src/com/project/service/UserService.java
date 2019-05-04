package com.project.service;

import javax.servlet.http.HttpSession;

import com.project.converters.UserConverter;
import com.project.model.UserModel;
import com.project.repository.UserRepository;
import com.project.utils.SessionUtils;


public class UserService {

	private UserRepository userRepository = new UserRepository();

	public int validateUser(String email, String password) {

		if (userRepository.userExists(email)) {

			if (getUserByEmail(email).getPassword().equals(password)) {
				return getUserByEmail(email).getUserId();
			}
			return 0;
		}

		return 0;
	}

	public UserModel getUserById(int userId) {
		return UserConverter.entityToModel(userRepository.getUserById(userId));
	}

	public UserModel getUserByEmail(String email) {
		return UserConverter.entityToModel(userRepository.getUserByEmail(email));
	}

	public boolean insertUser(String firstname, String lastname, String email, String password, String phoneNumber) {
		return userRepository.insertUser(firstname, lastname, email, password, phoneNumber);
	}
	
	public UserModel getLoggedUser() {
		UserModel user = null;
		try {
			HttpSession session = SessionUtils.getSession();
			if (session != null) {
				Integer userId = (Integer) session.getAttribute("userId");
				if (userId != null) {
					user = getUserById(userId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean userExists(String email) {
		if (email != null)
			return userRepository.userExists(email);
		return false;

	}
}
