package com.assignment.couponGenerator.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.assignment.couponGenerator.beans.UserData;

@Repository
public class JdbcUserRepository implements UserRepository {
	private JdbcTemplate jdbc;

	@Autowired
	JdbcUserRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Iterable<UserData> findAll() {
		return jdbc.query("select userId, name, userPhone from UserData", this::mapRowToUserData);
	}
	@Override
	public UserData findOne(String userId) {
		try{
			return jdbc.queryForObject("select userId, name, userPhone from UserData where userId=?",
					this::mapRowToUserData, userId);
		}
		catch(Exception e){
			return null;
		}
		
	}
	@Override
	public int save(UserData data) {
		try{
			return jdbc.update("insert into UserData (userId, name, userPhone) values (?, ?, ?)", data.getUserId(), data.getName(),
					data.getUserPhone());
		}
		catch(Exception e){
			return 0;
		}
	}
	private UserData mapRowToUserData(ResultSet rs, int rowNum) throws SQLException {
		return new UserData(rs.getString("userId"), rs.getString("userPhone"), rs.getString("name"));
	}
}
