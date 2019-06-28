package com.shinycatcher.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shinycatcher.api.dao.UserDao;
import com.shinycatcher.api.dto.EntryDetailsDto;

public class SuggestionsService {
	
	@Autowired
	UserDao userDao;
	
	public EntryDetailsDto getEntryDetails() {
		return new EntryDetailsDto();
	}
	
	public List<String> getUserNames() {
		return userDao.findUserNames();
	}

}
