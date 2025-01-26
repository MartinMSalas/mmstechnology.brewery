package com.mmstechnology.brewery.api_brewery.service;

import com.mmstechnology.brewery.api_brewery.web.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    public Optional<BeerDto> getBeerById(UUID beerId);

    Optional<BeerDto> saveNewBeer(BeerDto beerDto);

    Optional<BeerDto> updateBeer(UUID beerId, BeerDto beerDto);

    void deleteBeer(UUID beerId);
}
