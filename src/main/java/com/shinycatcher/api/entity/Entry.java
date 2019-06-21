package com.shinycatcher.api.entity;

import com.shinycatcher.api.dto.EntryDto;

public class Entry {

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
