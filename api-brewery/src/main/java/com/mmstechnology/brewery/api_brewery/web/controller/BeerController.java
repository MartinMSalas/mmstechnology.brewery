package com.mmstechnology.brewery.api_brewery.web.controller;

import com.mmstechnology.brewery.api_brewery.exception.BeerCantBeCreated;
import com.mmstechnology.brewery.api_brewery.exception.BeerNotFound;
import com.mmstechnology.brewery.api_brewery.service.BeerService;
import com.mmstechnology.brewery.api_brewery.service.BeerServiceImpl;
import com.mmstechnology.brewery.api_brewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Slf4j
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @Autowired
    public BeerController(BeerServiceImpl beerServiceImpl) {
        this.beerService = beerServiceImpl;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        return beerService.getBeerById(beerId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BeerNotFound("Beer not found with id: " + beerId));
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveNewBeer(@RequestBody BeerDto beerDto) {
        BeerDto beerDtoCreated = beerService.saveNewBeer(beerDto)
                .orElseThrow(() -> new BeerCantBeCreated("Beer can't be created with id: " + beerDto.getId()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + beerDtoCreated.getId().toString());
        return new ResponseEntity<>(beerDtoCreated, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
        BeerDto beerDtoUpdated = beerService.updateBeer(beerId, beerDto)
                .orElseThrow(() -> new BeerNotFound("Beer not found with id: " + beerId));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + beerDtoUpdated.getId().toString());
        return new ResponseEntity<>(beerDtoUpdated, headers, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        log.debug("In controller - Deleting beer with id: " + beerId);
        beerService.deleteBeer(beerId);
    }


}
