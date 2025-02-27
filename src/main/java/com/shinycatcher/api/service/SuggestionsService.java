package com.shinycatcher.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinycatcher.api.dao.BallDao;
import com.shinycatcher.api.dao.CaptureMethodDao;
import com.shinycatcher.api.dao.PokemonDao;
import com.shinycatcher.api.dao.UserDao;
import com.shinycatcher.api.dao.mapper.PokedexDao;
import com.shinycatcher.api.dto.EntryDetailsDto;

@Service
public class SuggestionsService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	BallDao ballDao;
	@Autowired
	CaptureMethodDao captureMethodDao;
	@Autowired
	PokemonDao pokemonDao;
	@Autowired
	PokedexDao pokedexDao;

	public EntryDetailsDto getEntryDetails() {
		EntryDetailsDto entryDetailsDto = new EntryDetailsDto();
		entryDetailsDto.balls = ballDao.findAll();
		entryDetailsDto.captureMethods = captureMethodDao.findAll();
		entryDetailsDto.pokemon = pokemonDao.findAll();
		entryDetailsDto.pokedexes = pokedexDao.findAll();
		return entryDetailsDto;
	}
	
	public List<String> getUserNames() {
		return userDao.findUserNames();
	}

}
