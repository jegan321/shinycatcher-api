package com.shinycatcher.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shinycatcher.api.dao.mapper.CaptureMethodRowMapper;
import com.shinycatcher.api.entity.CaptureMethod;

@Repository
public class CaptureMethodDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<CaptureMethod> findAll() {
        String sql = "SELECT * FROM capture_method";
        return jdbcTemplate.query(sql, new CaptureMethodRowMapper());
    }
}
