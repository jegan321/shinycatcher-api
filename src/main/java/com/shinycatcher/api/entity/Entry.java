package com.shinycatcher.api.entity;

import org.springframework.data.annotation.Id;

import com.shinycatcher.api.dto.EntryDto;
import com.shinycatcher.api.dto.UserDto;

public class Entry {

	@Id
	public Long entryId;
	public Long pokemonPokedexId;
	public Long ballId;
	public Long captureMethodId;
	
	public EntryDto toDto() {
		EntryDto dto = new EntryDto();
		dto.entryId = entryId;
		dto.pokemonPokedexId = pokemonPokedexId;
		dto.ballId = ballId;
		dto.captureMethodId = captureMethodId;
		return dto;
	}
}
