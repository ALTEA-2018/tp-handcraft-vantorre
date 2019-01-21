package com.miage.altea.controller;

import annotations.Controller;
import annotations.RequestMapping;
import com.miage.altea.bo.PokemonType;
import com.miage.altea.repository.PokemonTypeRepository;

import java.util.Map;

@Controller
public class PokemonTypeController {

    PokemonTypeRepository pokemonTypeRepository = new PokemonTypeRepository();

    @RequestMapping(uri = "/pokemons")
    public PokemonType getPokemon(Map<String, String[]> parameters) {
        if (parameters == null) {
            throw new IllegalArgumentException("parameters should not be empty");
        } else if (parameters.containsKey("id")) {
            return pokemonTypeRepository.findPokemonById(Integer.parseInt(parameters.get("id")[0]));
        } else if (parameters.containsKey("name")) {
            return pokemonTypeRepository.findPokemonByName(parameters.get("name")[0]);
        } else {
            throw new IllegalArgumentException("unknown parameter");
        }
    }


}