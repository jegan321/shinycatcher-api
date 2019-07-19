package com.shinycatcher.api.dao;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class EntryDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Long insert(Long userId, Long pokemonPokedexId, Long ballId, Long captureMethodId) {
		String sql = "INSERT INTO entry (user_id, pokemon_pokedex_id, ball_id, capture_method_id) VALUES (?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[] { "entry_id" });
			ps.setLong(1, userId);
			ps.setLong(2, pokemonPokedexId);
			ps.setLong(3, ballId);
			ps.setLong(4, captureMethodId);
			return ps;
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

}
