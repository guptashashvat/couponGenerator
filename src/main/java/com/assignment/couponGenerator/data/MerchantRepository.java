package com.assignment.couponGenerator.data;

import com.assignment.couponGenerator.beans.MerchantData;

public interface MerchantRepository {
	Iterable<MerchantData> findAll();
	MerchantData save(MerchantData data);
}
