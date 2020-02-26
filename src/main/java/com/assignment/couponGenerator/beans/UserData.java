package com.assignment.couponGenerator.beans;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
@NotNull
@Size(min=5, message="User Id must be at least 5 characters long")
private String userId;
@NotNull
@Digits(integer=10, fraction=0, message="Invalid phone number")
private String userPhone;
@NotNull
@NotBlank(message="Name is required")
private String name;
}
