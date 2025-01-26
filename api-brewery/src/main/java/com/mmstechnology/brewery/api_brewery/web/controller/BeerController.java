package com.mmstechnology.brewery.api_brewery.web.controller;

import com.mmstechnology.brewery.api_brewery.exception.BeerNotFound;
import com.mmstechnology.brewery.api_brewery.service.BeerService;
import com.mmstechnology.brewery.api_brewery.service.BeerServiceImpl;
import com.mmstechnology.brewery.api_brewery.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @Autowired
    public BeerController(BeerServiceImpl beerServiceImpl) {
        this.beerService = beerServiceImpl;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        return beerService.getBeerById(beerId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BeerNotFound("Beer not found with id: " + beerId));
    }
}
