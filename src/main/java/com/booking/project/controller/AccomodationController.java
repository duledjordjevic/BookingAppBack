package com.booking.project.controller;

import com.booking.project.model.Accomodation;
import com.booking.project.service.interfaces.IAccomodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/accomodations")
public class AccomodationController {

    @Autowired
    private IAccomodationService accomodationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Accomodation>> getAccomodations(){
        Collection<Accomodation> accomodations = accomodationService.findAll();
        return new ResponseEntity<Collection<Accomodation>>(accomodations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Accomodation>> getAccomodation(@PathVariable("id") Long id){
        Optional<Accomodation> accomodation = accomodationService.findById(id);
        if(accomodation == null){
            return new ResponseEntity<Optional<Accomodation>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Accomodation>>(accomodation, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Accomodation> createAccomodation(@RequestBody Accomodation accomodation) throws Exception {
        Accomodation savedAccomodation = accomodationService.save(accomodation);
        return new ResponseEntity<Accomodation>(savedAccomodation, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Accomodation> updateAccomodation(@RequestBody Accomodation accomodation, @PathVariable Long id) throws Exception{
        Optional<Accomodation> accomodationForUpdate = accomodationService.findById(id);

        if (accomodationForUpdate.isEmpty()){
            return new ResponseEntity<Accomodation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        accomodationForUpdate.get().copyValues(accomodation);


        return new ResponseEntity<Accomodation>(accomodationService.save(accomodationForUpdate.get()), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Accomodation> deleteAccomodation(@PathVariable("id") Long id){
        accomodationService.deleteById(id);
        return new ResponseEntity<Accomodation>(HttpStatus.NO_CONTENT);
    }
}
