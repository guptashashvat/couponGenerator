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
}
