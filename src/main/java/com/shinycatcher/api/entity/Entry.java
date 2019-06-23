package com.shinycatcher.api.entity;

import com.shinycatcher.api.dto.EntryDto;

public class Entry {

	public Long entryId;
//	public Long pokemonPokedexId;
//	public Long ballId;
//	public Long captureMethodId;
	public Pokemon pokemon;
	public Pokedex pokedex;
	public CaptureMethod captureMethod;
	public Ball ball;
	
	
	public EntryDto toDto() {
		EntryDto dto = new EntryDto();
		dto.entryId = entryId;
		if (captureMethod != null) {
			dto.captureMethodName= captureMethod.captureMethodName;
		}
		if (ball != null) {
			dto.ballName = ball.ballName;
			dto.ballImage = ball.ballImage;
		}
		if (pokemon != null) {
			dto.pokemonName = pokemon.pokemonName;
		}
		if (pokedex != null) {
			dto.pokedexName = pokedex.pokedexName;
		}
		return dto;
	}
}
