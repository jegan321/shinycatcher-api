package com.shinycatcher.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.shinycatcher.api.entity.Ball;
import com.shinycatcher.api.entity.CaptureMethod;
import com.shinycatcher.api.entity.Pokedex;
import com.shinycatcher.api.entity.Pokemon;

public class EntryDetailsDto {
	
	public List<Ball> balls = new ArrayList<>();
	public List<CaptureMethod> captureMethods = new ArrayList<>();
	public List<Pokemon> pokemon = new ArrayList<>();
	public List<Pokedex> pokedexes = new ArrayList<>();

}
