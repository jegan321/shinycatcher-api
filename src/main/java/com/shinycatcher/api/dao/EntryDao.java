package com.shinycatcher.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shinycatcher.api.entity.Entry;
import com.shinycatcher.api.entity.User;

@Repository
public class EntryDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insert(Entry entry, Long userId) {
		String sql = "INSERT INTO entry (user_id, pokemon_pokedex_id, ball_id, capture_method_id) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, userId, entry.pokedex.pokedexId, entry.ball.ballId, entry.captureMethod.captureMethodId);
	}

}
