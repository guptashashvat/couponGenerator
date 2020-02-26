package com.assignment.couponGenerator.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.assignment.couponGenerator.beans.MerchantData;

@Repository
public class JdbcMerchantRepository implements MerchantRepository {
	private JdbcTemplate jdbc;

	@Autowired
	JdbcMerchantRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	@Override
	public Iterable<MerchantData> findAll() {
		return jdbc.query("select merchantId, merchantName from MerchantData", this::mapRowToUserData);
	}
	@Override
	public MerchantData save(MerchantData data) {
		jdbc.update("insert into MerchantData (merchantId, merchantName) values (?, ?)", data.getMerchantId(), data.getMerchantName());
		return data;
	}
	private MerchantData mapRowToUserData(ResultSet rs, int rowNum) throws SQLException {
		return new MerchantData(rs.getString("merchantId"), rs.getString("merchantName"));
	}
}
