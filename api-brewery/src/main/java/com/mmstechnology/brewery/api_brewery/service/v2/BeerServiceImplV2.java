package com.mmstechnology.brewery.api_brewery.service.v2;


import com.mmstechnology.brewery.api_brewery.web.model.BeerDto;
import com.mmstechnology.brewery.api_brewery.web.model.v2.BeerDtoV2;
import com.mmstechnology.brewery.api_brewery.web.model.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImplV2 implements BeerServiceV2 {



    @Override
    public Optional<BeerDtoV2> getBeerById(UUID beerId) {
        return Optional.of(BeerDtoV2.builder().id(beerId).beerName("Galaxy Cat").beerStyle(BeerStyleEnum.IPA).build());
    }

    @Override
    public Optional<BeerDtoV2> saveNewBeer(BeerDtoV2 beerDto) {

        beerDto.setId(UUID.randomUUID());

        return Optional.of(beerDto);

    }

    @Override
    public Optional<BeerDtoV2> updateBeer(UUID beerId, BeerDtoV2 beerDto) {
        beerDto.setId(beerId);
        return Optional.of(beerDto);
    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.debug("Deleting beer with id: " + beerId);
    }

}
