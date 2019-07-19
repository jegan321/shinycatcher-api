package com.shinycatcher.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinycatcher.api.dao.EntryDao;
import com.shinycatcher.api.dao.PokemonPokedexDao;
import com.shinycatcher.api.dao.UserDao;
import com.shinycatcher.api.dto.EntryDto;
import com.shinycatcher.api.entity.User;

@Service
public class EntryService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	EntryDao entryDao;
	@Autowired
	PokemonPokedexDao pokemonPokedexDao;
	
	public Long postEntry(String userName, EntryDto entryDto) {
		User user = userDao.findByUserName(userName);
		Long pokemonPokedexId = pokemonPokedexDao.findPokemonPokedexId(entryDto.pokemonId, entryDto.pokedexId);
		return entryDao.insert(user.userId, pokemonPokedexId, entryDto.ballId, entryDto.captureMethodId);
	}

}
