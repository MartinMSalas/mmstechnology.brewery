package com.mmstechnology.brewery.api_brewery.service;


import com.mmstechnology.brewery.api_brewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {



    @Override
    public Optional<BeerDto> getBeerById(UUID beerId) {
        return Optional.of(BeerDto.builder().id(beerId).beerName("Galaxy Cat").beerStyle("Pale Ale").build());
    }

    @Override
    public Optional<BeerDto> saveNewBeer(BeerDto beerDto) {

        beerDto.setId(UUID.randomUUID());

        return Optional.of(beerDto);

    }

    @Override
    public Optional<BeerDto> updateBeer(UUID beerId, BeerDto beerDto) {
        beerDto.setId(beerId);
        return Optional.of(beerDto);
    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.debug("Deleting beer with id: " + beerId);
    }

}
