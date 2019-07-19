package com.shinycatcher.api.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shinycatcher.api.entity.Ball;

public class BallRowMapper implements RowMapper<Ball> {

    @Override
    public Ball mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ball ball = new Ball();
        ball.ballId = rs.getLong("ball_id");
        ball.ballImage = rs.getString("ball_image");
        ball.ballName = rs.getString("ball_name");

        return ball;
    }
}
