package com.shinycatcher.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PokemonPokedexDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Long findPokemonPokedexId(Long pokemonId, Long pokedexId) {
        String sql = "SELECT pokemon_pokedex_id FROM pokemon_pokedex WHERE pokemon_id=? AND pokedex_id=?";
        return jdbcTemplate.queryForObject(sql, new Long[] {pokemonId, pokedexId}, (rs, rowNum) -> rs.getLong("pokemon_pokedex_id"));
    }

}
