package com.shinycatcher.api.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shinycatcher.api.entity.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.userId = rs.getLong("user_id");
		user.userName = rs.getString("user_name");
		user.userEmail = rs.getString("user_email");
		user.userStatus = rs.getString("user_status");
		return user;
	}

}
