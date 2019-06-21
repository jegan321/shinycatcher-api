package com.shinycatcher.api.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shinycatcher.api.entity.Entry;

public class EntryRowMapper implements RowMapper<Entry> {

	@Override
	public Entry mapRow(ResultSet rs, int rowNum) throws SQLException {
		Entry entry = new Entry();
		entry.entryId = rs.getLong("entry_id");
		return entry;
	}

}
