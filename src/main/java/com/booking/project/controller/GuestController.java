package com.booking.project.controller;

import com.booking.project.model.Guest;
import com.booking.project.model.User;
import com.booking.project.service.interfaces.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    @Autowired
    private IGuestService guestService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Guest>> getGuests(){
        Collection<Guest> guests = guestService.findAll();
        return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Guest>> getGuest(@PathVariable("id") Long id){
        Optional<Guest> guest = guestService.findById(id);
        if(guest.isEmpty()){
            return new ResponseEntity<Optional<Guest>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Guest>>(guest, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) throws Exception {
        Guest savedGuest = guestService.save(guest);
        return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> updateGuest(@RequestBody Guest guest, @PathVariable Long id) throws Exception{
        Optional<Guest> guestForUpdate = guestService.findById(id);

        if (guestForUpdate.isEmpty()) {
            return new ResponseEntity<Guest>(HttpStatus.BAD_REQUEST);
        }

        guestForUpdate.get().copyValues(guest);

        return new ResponseEntity<Guest>(guestService.save(guestForUpdate.get()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Guest> deleteGuest(@PathVariable("id") Long id){
        guestService.deleteById(id);
        return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
    }

}
