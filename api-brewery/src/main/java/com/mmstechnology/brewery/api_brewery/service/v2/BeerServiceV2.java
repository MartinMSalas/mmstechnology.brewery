package com.mmstechnology.brewery.api_brewery.service.v2;

import com.mmstechnology.brewery.api_brewery.web.model.BeerDto;
import com.mmstechnology.brewery.api_brewery.web.model.v2.BeerDtoV2;

import java.util.Optional;
import java.util.UUID;

public interface BeerServiceV2 {

    public Optional<BeerDtoV2> getBeerById(UUID beerId);

    Optional<BeerDtoV2> saveNewBeer(BeerDtoV2 beerDto);

    Optional<BeerDtoV2> updateBeer(UUID beerId, BeerDtoV2 beerDto);

    void deleteBeer(UUID beerId);
}
