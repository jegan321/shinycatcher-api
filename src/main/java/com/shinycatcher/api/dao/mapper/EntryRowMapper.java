package com.shinycatcher.api.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shinycatcher.api.entity.Ball;
import com.shinycatcher.api.entity.CaptureMethod;
import com.shinycatcher.api.entity.Entry;
import com.shinycatcher.api.entity.Pokedex;
import com.shinycatcher.api.entity.Pokemon;

public class EntryRowMapper implements RowMapper<Entry> {

	@Override
	public Entry mapRow(ResultSet rs, int rowNum) throws SQLException {
		Entry entry = new Entry();
		entry.entryId = rs.getLong("entry_id");
		entry.nickname = rs.getString("nickname");
		if (rs.getObject("capture_method_id") != null) {
			CaptureMethod captureMethod = new CaptureMethod();
			captureMethod.captureMethodId = rs.getLong("capture_method_id");
			captureMethod.captureMethodName = rs.getString("capture_method_name");
			entry.captureMethod = captureMethod;
		}
		if (rs.getObject("ball_id") != null) {
			Ball ball = new Ball();
			ball.ballId = rs.getLong("ball_id");
			ball.ballName = rs.getString("ball_name");
			ball.ballImage = rs.getString("ball_image");
			entry.ball = ball;
		}
		if (rs.getObject("pokemon_id") != null) {
			Pokemon pokemon = new Pokemon();
			pokemon.pokemonId = rs.getLong("pokemon_id");
			pokemon.pokemonName = rs.getString("pokemon_name");
			entry.pokemon = pokemon;
		}
		if (rs.getObject("pokedex_id") != null) {
			Pokedex pokedex = new Pokedex();
			pokedex.pokedexId = rs.getLong("pokedex_id");
			pokedex.pokedexName = rs.getString("pokedex_name");
			entry.pokedex = pokedex;
		}
		return entry;
	}

}
