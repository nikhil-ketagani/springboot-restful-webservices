package com.nikhil.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.nikhil.springboot.dto.UserDTO;
import com.nikhil.springboot.entity.User;

@Mapper
public interface AutoUserMapper {
AutoUserMapper MAPPER= Mappers.getMapper(AutoUserMapper.class);
	UserDTO mapToUserDTO(User user);
	User maptoUser(UserDTO userDto);
}
