package com.shinycatcher.api.dao.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.shinycatcher.api.dao.mapper.EntryRowMapper;
import com.shinycatcher.api.dao.mapper.UserRowMapper;
import com.shinycatcher.api.entity.Entry;
import com.shinycatcher.api.entity.User;

public class UserAggregateExtractor implements ResultSetExtractor<List<User>> {

	/**
	 * Converts the result set into a nested object structure of users and entries. This should be 
	 * used with a join query to get all the objects in one database call.
	 */
	@Override
	public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		// Keep track of each user in the result set
		Map<Long, User> users = new HashMap<>();
		
		while (rs.next()) {
			
			User user = new UserRowMapper().mapRow(rs, 0);
			
			if (!users.containsKey(user.userId)) {
				
				// If user isn't in the map yet add it
				users.put(user.userId, user);
			} else {
				
				// If user is already in the map, use the object in the map  
				// because it may already have entries in it
				user = users.get(user.userId);
			}
			
			// Add the entry for the user if there is one
			if (rs.getObject("entry_id") != null) {
				Entry entry = new EntryRowMapper().mapRow(rs, 0);
				user.entries.add(entry);
			}
		}
		
		// Convert the user map into an ArrayList
		return new ArrayList<>(users.values());
	}

}
