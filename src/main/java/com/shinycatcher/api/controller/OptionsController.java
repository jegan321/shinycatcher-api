package com.shinycatcher.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinycatcher.api.dto.EntryOptionsDto;

@RestController
public class OptionsController {
	
	@RequestMapping(value="/options", method=RequestMethod.GET)
	public EntryOptionsDto getOptions() {
		//TODO: return an object with an array for each of the entry options
		return new EntryOptionsDto();
	}

}
