package com.shinycatcher.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shinycatcher.api.dao.extractor.UserAggregateExtractor;
import com.shinycatcher.api.dao.mapper.UserRowMapper;
import com.shinycatcher.api.entity.User;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<User> findAll() {
		String sql = "SELECT * FROM user";
		return jdbcTemplate.query(sql, new UserRowMapper());
	}
	
	public User findByUserName(String userName) {
		String sql = "SELECT * FROM user "
				+ "LEFT JOIN entry ON entry.user_id=user.user_id "
				+ "LEFT JOIN pokemon_pokedex ON pokemon_pokedex.pokemon_pokedex_id=entry.pokemon_pokedex_id "
				+ "LEFT JOIN pokemon ON pokemon.pokemon_id=pokemon_pokedex.pokemon_id "
				+ "LEFT JOIN pokedex ON pokedex.pokedex_id=pokemon_pokedex.pokedex_id "
				+ "LEFT JOIN capture_method ON capture_method.capture_method_id=entry.capture_method_id "
				+ "LEFT JOIN ball ON ball.ball_id=entry.ball_id "
				+ "WHERE user_name = ?";
		List<User> users = jdbcTemplate.query(sql, new String[] {userName}, new UserAggregateExtractor());
		return DataAccessUtils.requiredSingleResult(users);
	}
	
	public void insert(User user) {
		String sql = "INSERT INTO user (user_name, user_email, user_status, user_password, salt) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sql, user.userName, user.userEmail, user.userStatus, user.userPassword, user.salt);
	}
	
	public void update(User user) {
		String sql = "UPDATE user SET user_name=?, user_email=?, user_status=? WHERE user_id=?";
		jdbcTemplate.update(sql, user.userName, user.userEmail, user.userStatus, user.userId);
	}
	
	public void updatePassword(String userPassword, String userName) {
		String sql = "UPDATE user SET user_password=? WHERE user_name=?";
		jdbcTemplate.update(sql, userPassword, userName);
	}
	
	public void updateSessionToken(String sessionToken, Long sessionTokenIssuedTime, String userName) {
		String sql = "UPDATE user SET session_token=?, session_token_issued_time=? WHERE user_name=?";
		jdbcTemplate.update(sql, sessionToken, sessionTokenIssuedTime, userName);
	}
	
	public void delete(User user) {
		String sql = "DELETE user WHERE user_id=?";
		jdbcTemplate.update(sql, user.userId);
	}
	
	public List<String> findUserNames() {
		String sql = "SELECT user_name FROM user";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("user_name"));
	}
	
	public List<String> findEditorUserNames(String userName) {
		String sql = "SELECT editor.user_name AS editor_name FROM user AS owner "
				+ "INNER JOIN owner_editor ON owner_editor.owner_id=owner.user_id "
				+ "INNER JOIN user AS editor ON editor.user_id=owner_editor.editor_id "
				+ "WHERE owner.user_name=?";
		return jdbcTemplate.query(sql, new String[] {userName}, (rs, rowNum) -> rs.getString("editor_name"));
	}

}