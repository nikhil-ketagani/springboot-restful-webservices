package com.nikhil.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nikhil.springboot.dto.UserDTO;
import com.nikhil.springboot.entity.User;
import com.nikhil.springboot.exceptions.EmailAlreadyExistException;
import com.nikhil.springboot.exceptions.ResourceNotFoundException;
import com.nikhil.springboot.mapper.AutoUserMapper;
import com.nikhil.springboot.mapper.UserMapper;
import com.nikhil.springboot.repository.UserRepository;

import com.nikhil.springboot.service.UserService;

import jakarta.transaction.UserTransaction;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

	private   UserRepository userRepo;
	private   ModelMapper modelMapper;
	@Override 
	public UserDTO createUser(UserDTO userDTO) {
		//Covert userDTO to user JPA entity
		//User user = modelMapper.map(userDTO, User.class);
		//User user = UserMapper.mapToUser(userDTO);
		
		if(userRepo.findByEmail(userDTO.getEmail()).isPresent()) {
			throw new EmailAlreadyExistException(String.format("email %s already exists",userDTO.getEmail()));
		}
		
		User user = AutoUserMapper.MAPPER.maptoUser(userDTO);
	
		User savedUser = userRepo.save(user);
		//convert user JPA entity to UserDto
		//UserDTO savedUserDTO = UserMapper.mapToUserDTO(savedUser);
		//UserDTO savedUserDTO = modelMapper.map(savedUser,UserDTO.class);
	UserDTO savedUserDTO = AutoUserMapper.MAPPER.mapToUserDTO(savedUser);
	return savedUserDTO;
	
	
	}
	@Override
	public UserDTO getUserById(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
		//UserDTO savedUserDTO = UserMapper.mapToUserDTO(user);
		//UserDTO savedUserDTO = modelMapper.map(user,UserDTO.class);
		UserDTO savedUserDTO = AutoUserMapper.MAPPER.mapToUserDTO(user);
		return savedUserDTO;
	}
	@Override
	public List<UserDTO> getAlluser() {
		
		List<User> users = userRepo.findAll();
		//List<UserDTO> UserDTOList = new ArrayList<>(users.size());
		
		//users.forEach(user-> UserDTOList.add(UserMapper.mapToUserDTO(user)));
		
		//return users.stream().map(user->modelMapper.map(user,UserDTO.class)).collect(Collectors.toList());
return users.stream().map(user -> AutoUserMapper.MAPPER.mapToUserDTO(user)).collect(Collectors.toList());
	}
	@Override
	public UserDTO updateUser(UserDTO userDTO) {
	//User user = UserMapper.mapToUser(userDTO);
	//	User user = modelMapper.map(userDTO,User.class);	
		User user = AutoUserMapper.MAPPER.maptoUser(userDTO);
	
	User existingUser = userRepo.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("User","id", user.getId()));
	existingUser.setFirstName(user.getFirstName());
	existingUser.setLastName(user.getLastName());
	existingUser.setEmail(user.getEmail());
	User updatedUser = userRepo.save(existingUser);
	
	//UserDTO updatedUserDTO = UserMapper.mapToUserDTO(updatedUser);
	//UserDTO updatedUserDTO = modelMapper.map(updatedUser,UserDTO.class);
	UserDTO updatedUserDTO = AutoUserMapper.MAPPER.mapToUserDTO(updatedUser);
	return updatedUserDTO;
	}
	@Override
	public void deleteUser(Long userid) {
		User existingUser = userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User","id", userid));
		userRepo.deleteById(userid);

	}

}
