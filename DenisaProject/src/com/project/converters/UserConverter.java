package com.project.converters;

import com.project.entity.User;
import com.project.model.UserModel;

public class UserConverter {
	
	public static UserModel entityToModel(User user) {
		if (user != null) {
			UserModel userModel = new UserModel();
			userModel.setUserId(user.getUserId());
			userModel.setFirstname(user.getFirstname());
			userModel.setLastname(user.getLastname());
			userModel.setEmail(user.getEmail());
			userModel.setPassword(user.getPassword());
			userModel.setPhoneNumber(user.getPhoneNumber());
			userModel.setRole(user.getRole());
			return userModel;
		} else {
			return null;
		}
	}
	
	public static User modelToEntity(UserModel userModel) {
		if (userModel != null) {
			User user = new User();
			user.setUserId(userModel.getUserId());
			user.setFirstname(userModel.getFirstname());
			user.setLastname(userModel.getLastname());
			user.setEmail(userModel.getEmail());
			user.setPassword(userModel.getPassword());
			user.setPhoneNumber(userModel.getPhoneNumber());
			user.setRole(userModel.getRole());
			return user;
		} else {
			return null;
		}
	}

}
