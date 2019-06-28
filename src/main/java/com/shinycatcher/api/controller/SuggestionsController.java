package com.shinycatcher.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinycatcher.api.dto.EntryDetailsDto;
import com.shinycatcher.api.service.SuggestionsService;

@RestController
public class SuggestionsController {
	
	@Autowired
	SuggestionsService suggestionsService;
	
	@RequestMapping(value="/suggestions/entry-details", method=RequestMethod.GET)
	public EntryDetailsDto getEntryDetails() {
		//TODO: return an object with an array for each of the entry options
		return new EntryDetailsDto();
	}
	
	@RequestMapping(value="/suggestions/users", method=RequestMethod.GET)
	public List<String> getUsers() {
		return suggestionsService.getUserNames();
	}

}
