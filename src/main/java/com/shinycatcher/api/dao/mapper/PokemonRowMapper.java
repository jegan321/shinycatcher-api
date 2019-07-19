package com.shinycatcher.api.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shinycatcher.api.entity.Pokemon;

public class PokemonRowMapper implements RowMapper<Pokemon> {

    @Override
    public Pokemon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pokemon pokemon = new Pokemon();
        pokemon.pokemonName = rs.getString("pokemon_name");
        pokemon.pokemonId = rs.getLong("pokemon_id");
        //TODO: entity needs additional fields??

        return pokemon;
    }
}
