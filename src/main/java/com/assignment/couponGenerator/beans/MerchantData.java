package com.assignment.couponGenerator.beans;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantData {
@NotBlank(message="Merchant Id is required")
private String merchantId;
@NotBlank(message="Merchant name is required")
private String merchantName;
}
