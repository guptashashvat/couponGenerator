package com.assignment.couponGenerator.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.assignment.couponGenerator.beans.CouponData;

@Repository
public class JdbcCouponRepository implements CouponRepository {

	private JdbcTemplate jdbc;

	@Autowired
	JdbcCouponRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	@Override
	public Iterable<CouponData> findAll() {
		return jdbc.query("select couponId, startDate, endDate, status, amount from CouponData", this::mapRowToCouponData);
	}
	@Override
	public Iterable<Map<String, String>> findCouponMapping() {
		return jdbc.query("select couponId, userId from CouponMapping", this::mapRowToCouponMapping);
	}
	@Override
	public Iterable<CouponData> findAssignedCoupons(String userId) {
		return jdbc.query("SELECT CouponData.couponId, CouponData.startDate, CouponData.endDate, CouponData.status, CouponData.amount FROM CouponData JOIN CouponMapping ON CouponData.couponId = CouponMapping.couponId WHERE CouponMapping.userId=?", this::mapRowToCouponData, userId);
	}
	@Override
	public Iterable<CouponData> findActive() {
		return jdbc.query("select couponId, startDate, endDate, status, amount from CouponData where status='ACTIVE' and endDate>=curDate()", this::mapRowToCouponData);
	}
	@Override
	public int assignCoupon(String userId, String couponId){
		jdbc.update("insert into CouponMapping (couponId, userId) values (?, ?)", couponId, userId);
		return jdbc.update("update CouponData set status='ASSIGNED' where couponId=?", couponId);
	}
	@Override
	public void redeemCoupon(String couponId){
		jdbc.update("delete from CouponMapping where couponId=?", couponId);
		jdbc.update("update CouponData set status='REDEEMED' where couponId=?", couponId);
	}
	@Override
	public CouponData save(CouponData data) {
		jdbc.update("insert into CouponData (couponId, startDate, endDate, status, amount) values (?, ?, ?, ?, ?)", data.getCouponId(), data.getStartDate(),
				data.getEndDate(), data.getStatus(), data.getAmount());
		return data;
	}
	private CouponData mapRowToCouponData(ResultSet rs, int rowNum) throws SQLException {
		return new CouponData(rs.getString("couponId"), rs.getString("startDate"), rs.getString("endDate"), rs.getString("status"), rs.getInt("amount"));
	}
	private Map<String, String> mapRowToCouponMapping(ResultSet rs, int rowNum) throws SQLException {
		Map<String, String> hm=new HashMap<>();
		hm.put("couponId", rs.getString("couponId"));
		hm.put("userId", rs.getString("userId"));
		return hm;
	}
}
