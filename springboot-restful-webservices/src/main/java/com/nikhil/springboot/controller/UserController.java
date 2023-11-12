package com.nikhil.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.springboot.dto.UserDTO;
import com.nikhil.springboot.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@Tag(name = "CRUD REST APIs for User Resource",
	description="Create User,Update user,Get user,Get All user,Delete user "	
		)

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

 private UserService userService;
 @Operation(
		 summary = "Create User REST API",
		 description = "Create User REST API is used to save user in a database"
		 )
 @ApiResponse(
		 responseCode = "201",
		 description="HTTP Status 201 Created"
		 )
 @PostMapping("")
 public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO user){
	 UserDTO savedUser = userService.createUser(user);
return new ResponseEntity<UserDTO>(savedUser,HttpStatus.CREATED);
 }
 
 
 @Operation(
		 summary = "GET User REST API",
		 description = "GET User REST API is used to get a single user in a database"
		 )
 @ApiResponse(
		 responseCode = "200",
	description="HTTP Status 200 Success"
		 )
 //localhost:8080/users/1
 @GetMapping("{id}")
 public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long userId) {
	 UserDTO userDto = userService.getUserById(userId);
return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);	 
 }
 
 @Operation(
		 summary = "GET ALL Users REST API",
		 description = "GET ALL Users REST API is used to get all the users in a database"
		 )
 @ApiResponse(
		 responseCode = "200",
	description="HTTP Status 200 Success"
		 )
 @GetMapping("")
 public ResponseEntity<List<UserDTO>> getAllUsers(){
	 List<UserDTO> alluser = userService.getAlluser();
	 return new ResponseEntity<List<UserDTO>>(alluser,HttpStatus.OK);
 }
 
 @Operation(
		 summary = "UPDATE  User REST API",
		 description = "UPDATE  User REST API is used to update a particular user in a database"
		 )
 @ApiResponse(
		 responseCode = "200",
	description="HTTP Status 200 Success"
		 )
 @PutMapping("{id}")
 public ResponseEntity<UserDTO> updateUser(@PathVariable("id")Long userId,@RequestBody @Valid  UserDTO userDto){
	 userDto.setId(userId);
 UserDTO updateUser = userService.updateUser(userDto);
 return new ResponseEntity<UserDTO>(updateUser,HttpStatus.OK);
 }
 
 @Operation(
		 summary = "DELETE User REST API",
		 description = "DELETE User REST API is used to DELETE a particular user in a database"
		 )
 @ApiResponse(
		 responseCode = "200",
	description="HTTP Status 200 Success"
		 )
 @DeleteMapping("{id}")
 public ResponseEntity<String> deleteUser(@PathVariable("id")Long userid){
	 userService.deleteUser(userid);
	 return new ResponseEntity<String>("user successfully deleted",HttpStatus.OK);
 }
 
 }

