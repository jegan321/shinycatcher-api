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
		return suggestionsService.getEntryDetails();
	}
	
	@RequestMapping(value="/suggestions/users", method=RequestMethod.GET)
	public List<String> getUsers() {
		return suggestionsService.getUserNames();
	}

}
