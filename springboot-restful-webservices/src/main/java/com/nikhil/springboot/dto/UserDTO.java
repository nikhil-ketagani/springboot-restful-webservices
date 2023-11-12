package com.nikhil.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
description = "UserDTO model information"		
		)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
private Long Id;

@Schema(description = "User First name")
@NotEmpty(message = "user firstname should not be null or empty")
private String firstName;

@Schema(description = "User Last name")
@NotEmpty(message = "user lastname should not be null or empty")
private String lastName;

@Schema(description = "User Email address")
@NotEmpty(message = "user email should not be null or empty")
@Email
private String email;
}
