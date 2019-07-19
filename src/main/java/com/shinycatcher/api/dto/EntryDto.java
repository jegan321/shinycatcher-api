package com.shinycatcher.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class EntryDto {
	
	public Long entryId;

	public Long pokemonId;
	public String pokemonName;
	public String pokemonImage;

	public Long pokedexId;
	public String pokedexName;
	
	public Long ballId;
	public String ballName;
	public String ballImage;
	
	public Long captureMethodId;
	public String captureMethodName;

}
