package com.assignment.couponGenerator.data;

import java.util.Map;
import com.assignment.couponGenerator.beans.CouponData;

public interface CouponRepository {
	Iterable<CouponData> findAll();
	Iterable<CouponData> findAssignedCoupons(String userId);
	Iterable<Map<String, String>> findCouponMapping();
	Iterable<CouponData> findActive();
	int assignCoupon(String userId, String couponId);
	void redeemCoupon(String couponId);
	CouponData save(CouponData data);
}
