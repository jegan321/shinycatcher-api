package com.shinycatcher.api.dao;

import com.shinycatcher.api.dao.mapper.CaptureMethodRowMapper;
import com.shinycatcher.api.entity.CaptureMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CaptureMethodDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<CaptureMethod> findAll() {
        String sql = "SELECT * FROM capture_method";
        return jdbcTemplate.query(sql, new CaptureMethodRowMapper());
    }
}
