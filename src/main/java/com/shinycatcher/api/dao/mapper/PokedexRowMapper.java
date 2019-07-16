package com.shinycatcher.api.dao.mapper;

import com.shinycatcher.api.entity.Pokedex;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PokedexRowMapper implements RowMapper<Pokedex> {

    @Override
    public Pokedex mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pokedex pokedex = new Pokedex();
        pokedex.pokedexId = rs.getLong("pokedex_id");
        pokedex.pokedexName = rs.getString("pokedex_name");

        return pokedex;
    }
}
