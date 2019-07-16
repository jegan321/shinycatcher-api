package com.shinycatcher.api.dao.mapper;

import com.shinycatcher.api.entity.Pokedex;
import com.shinycatcher.api.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PokedexDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Pokedex> findAll() {
        String sql = "SELECT * FROM pokedex";
        return jdbcTemplate.query(sql, new PokedexRowMapper());
    }
}
