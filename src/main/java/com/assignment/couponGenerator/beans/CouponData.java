package com.assignment.couponGenerator.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponData {
private String couponId;
private String startDate;
private String endDate;
private String status;
private int amount;

/*CouponData(String couponId, String startDate, String endDate){
	this.couponId=couponId;
	this.startDate=startDate;
	this.endDate=endDate;
}
public String getCouponId(){
	return couponId;
}
public String getStartDate(){
	return startDate;
}
public String getEndDate(){
	return endDate;
}*/
}
