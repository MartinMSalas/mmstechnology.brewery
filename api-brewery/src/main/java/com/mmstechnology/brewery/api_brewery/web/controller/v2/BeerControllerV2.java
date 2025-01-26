package com.mmstechnology.brewery.api_brewery.web.controller.v2;

import com.mmstechnology.brewery.api_brewery.exception.BeerCantBeCreated;
import com.mmstechnology.brewery.api_brewery.exception.BeerNotFound;
import com.mmstechnology.brewery.api_brewery.service.v2.BeerServiceImplV2;
import com.mmstechnology.brewery.api_brewery.service.v2.BeerServiceV2;
import com.mmstechnology.brewery.api_brewery.web.model.v2.BeerDtoV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Slf4j
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;

    @Autowired
    public BeerControllerV2(BeerServiceImplV2 beerServiceImpl) {
        this.beerServiceV2 = beerServiceImpl;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeerById(@PathVariable("beerId") UUID beerId) {
        return beerServiceV2.getBeerById(beerId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BeerNotFound("Beer not found with id: " + beerId));
    }

    @PostMapping
    public ResponseEntity<BeerDtoV2> saveNewBeer(@RequestBody BeerDtoV2 beerDtoV2) {
        BeerDtoV2 beerDtoCreated = beerServiceV2.saveNewBeer(beerDtoV2)
                .orElseThrow(() -> new BeerCantBeCreated("Beer can't be created with id: " + beerDtoV2.getId()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + beerDtoCreated.getId().toString());
        return new ResponseEntity<>(beerDtoCreated, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDtoV2 beerDtoV2) {
        BeerDtoV2 beerDtoUpdated = beerServiceV2.updateBeer(beerId, beerDtoV2)
                .orElseThrow(() -> new BeerNotFound("Beer not found with id: " + beerId));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + beerDtoUpdated.getId().toString());
        return new ResponseEntity<>(beerDtoUpdated, headers, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        log.debug("In controller - Deleting beer with id: " + beerId);
        beerServiceV2.deleteBeer(beerId);
    }


}
