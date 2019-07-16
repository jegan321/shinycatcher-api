package com.shinycatcher.api.dao;

import com.shinycatcher.api.dao.mapper.PokemonRowMapper;
import com.shinycatcher.api.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PokemonDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Pokemon> findAll() {
        String sql = "SELECT * FROM pokemon";
        return jdbcTemplate.query(sql, new PokemonRowMapper());
    }

}
