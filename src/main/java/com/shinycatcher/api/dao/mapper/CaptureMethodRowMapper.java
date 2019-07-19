package com.shinycatcher.api.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shinycatcher.api.entity.CaptureMethod;

public class CaptureMethodRowMapper implements RowMapper<CaptureMethod> {

    @Override
    public CaptureMethod mapRow(ResultSet rs, int rowNum) throws SQLException {
        CaptureMethod captureMethod = new CaptureMethod();
        captureMethod.captureMethodId = rs.getLong("capture_method_id");
        captureMethod.captureMethodName = rs.getString("capture_method_name");

        return captureMethod;
    }
}
