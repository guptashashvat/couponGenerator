package com.assignment.couponGenerator.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
private String userId;
private String userPhone;
private String name;
/*public UserData(String userId, String userPhone, String name){
	this.userId=userId;
	this.userPhone=userPhone;
	this.name=name;
}
public String getUserId(){
	return userId;
}
public String getUserPhone(){
	return userPhone;
}
public String getName(){
	return name;
}
public void setUserId(String userId){
	this.userId=userId;
}
public void setUserPhone(String userPhone){
	this.userPhone=userPhone;
}
public void setName(String name){
	this.name=name;
}*/
}
