package com.nikhil.springboot.mapper;

import com.nikhil.springboot.dto.UserDTO;
import com.nikhil.springboot.entity.User;

public class UserMapper {
	//CONVERT User JPA entity to UserDTO
public static UserDTO mapToUserDTO(User user) {
	UserDTO userDTO = new UserDTO(
			user.getId(),
			user.getFirstName(),
			user.getLastName(), 
			user.getEmail());
	return userDTO;
}
//CONVERT UserDTO to User JPA entity
public static User mapToUser(UserDTO userDTO) {
	User user = new User(
			userDTO.getId(),
			userDTO.getFirstName(),
			userDTO.getLastName(), 
			userDTO.getEmail());
	return user;
}
}
