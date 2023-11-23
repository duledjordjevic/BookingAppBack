package com.booking.project.controller;

import com.booking.project.model.Accommodation;
import com.booking.project.model.Reservation;
import com.booking.project.service.interfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Reservation>> getReservations(){
        Collection<Reservation> reservations = reservationService.findAll();
        return new ResponseEntity<Collection<Reservation>>(reservations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Reservation>> getReservation(@PathVariable("id") Long id){
        Optional<Reservation> reservation = reservationService.findById(id);
        if(reservation.isEmpty()){
            return new ResponseEntity<Optional<Reservation>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Reservation>>(reservation, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) throws Exception {
        Reservation savedReservation = reservationService.save(reservation);
        return new ResponseEntity<Reservation>(savedReservation, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation, @PathVariable Long id) throws Exception{
        Optional<Reservation> reservationForUpdate = reservationService.findById(id);

        if (reservationForUpdate.isEmpty()){
            return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        reservationForUpdate.get().copyValues(reservation);


        return new ResponseEntity<Reservation>(reservationService.save(reservationForUpdate.get()), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Reservation> deleteAccommodation(@PathVariable("id") Long id){
        reservationService.deleteById(id);
        return new ResponseEntity<Reservation>(HttpStatus.NO_CONTENT);
    }
}
