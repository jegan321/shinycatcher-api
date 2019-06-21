package com.shinycatcher.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shinycatcher.api.dao.extractor.UserAggregateExtractor;
import com.shinycatcher.api.entity.User;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<User> findAll() {
		String sql = "SELECT * FROM user LEFT JOIN entry ON user.user_id=entry.user_id";
		return jdbcTemplate.query(sql, new UserAggregateExtractor());
	}
	
	public List<User> findByUserName(String userName) {
		String sql = "SELECT * FROM user LEFT JOIN entry ON user.user_id=entry.user_id WHERE user_name = ?";
		return jdbcTemplate.query(sql, new String[] {userName}, new UserAggregateExtractor());
	}
	
	public User findById(Long userId) {
		String sql = "SELECT * FROM user LEFT JOIN entry ON user.user_id=entry.user_id WHERE user.user_id = ?";
		List<User> results = jdbcTemplate.query(sql, new Long[] {userId}, new UserAggregateExtractor());
		return DataAccessUtils.requiredSingleResult(results);
	}
	
	public void insert(User user) {
		String sql = "INSERT INTO user (user_name, user_email, user_status) VALUES (?,?,?)";
		jdbcTemplate.update(sql, user.userName, user.userEmail, user.userStatus);
	}
	
	public void update(User user) {
		String sql = "UPDATE user SET user_name=?, user_email=?, user_status=? WHERE user_id=?";
		jdbcTemplate.update(sql, user.userName, user.userEmail, user.userStatus, user.userId);
	}
	
	public void delete(User user) {
		String sql = "DELETE user WHERE user_id=?";
		jdbcTemplate.update(sql, user.userId);
	}

}