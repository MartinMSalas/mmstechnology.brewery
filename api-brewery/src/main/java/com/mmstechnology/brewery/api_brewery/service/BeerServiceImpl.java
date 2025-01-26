package com.mmstechnology.brewery.api_brewery.service;


import com.mmstechnology.brewery.api_brewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {



    @Override
    public Optional<BeerDto> getBeerById(UUID beerId) {
        return Optional.of(BeerDto.builder().id(beerId).beerName("Galaxy Cat").beerStyle("Pale Ale").build());
    }

}
