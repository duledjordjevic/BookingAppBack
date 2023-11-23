package com.booking.project.controller;

import com.booking.project.model.Accommodation;
import com.booking.project.service.interfaces.IAccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    @Autowired
    private IAccommodationService accommodationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Accommodation>> getAccommodations(){
        Collection<Accommodation> accommodations = accommodationService.findAll();
        return new ResponseEntity<Collection<Accommodation>>(accommodations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Accommodation>> getAccommodation(@PathVariable("id") Long id){
        Optional<Accommodation> accommodation = accommodationService.findById(id);
        if(accommodation.isEmpty()){
            return new ResponseEntity<Optional<Accommodation>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Accommodation>>(accommodation, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Accommodation> createAccommodation(@RequestBody Accommodation accommodation) throws Exception {
        Accommodation savedAccommodation = accommodationService.save(accommodation);
        return new ResponseEntity<Accommodation>(savedAccommodation, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Accommodation> updateAccommodation(@RequestBody Accommodation accommodation, @PathVariable Long id) throws Exception{
        Optional<Accommodation> accommodationForUpdate = accommodationService.findById(id);

        if (accommodationForUpdate.isEmpty()){
            return new ResponseEntity<Accommodation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        accommodationForUpdate.get().copyValues(accommodation);


        return new ResponseEntity<Accommodation>(accommodationService.save(accommodationForUpdate.get()), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Accommodation> deleteAccommodation(@PathVariable("id") Long id){
        accommodationService.deleteById(id);
        return new ResponseEntity<Accommodation>(HttpStatus.NO_CONTENT);
    }
}
