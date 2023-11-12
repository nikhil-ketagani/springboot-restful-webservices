package com.nikhil.springboot.service;

import java.util.List;

import com.nikhil.springboot.dto.UserDTO;
import com.nikhil.springboot.entity.User;

public interface UserService {
UserDTO createUser(UserDTO user);
UserDTO getUserById(Long userId);
List<UserDTO> getAlluser();
UserDTO updateUser(UserDTO user);
void deleteUser(Long userid);
}
