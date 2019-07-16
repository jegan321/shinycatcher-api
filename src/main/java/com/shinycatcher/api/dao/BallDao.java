package com.shinycatcher.api.dao;

import com.shinycatcher.api.dao.mapper.BallRowMapper;
import com.shinycatcher.api.entity.Ball;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BallDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Ball> findAll() {
        String sql = "SELECT * FROM ball";
        return jdbcTemplate.query(sql, new BallRowMapper());
    }
}
