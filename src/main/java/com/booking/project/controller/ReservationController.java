package com.booking.project.controller;

import com.booking.project.dto.CreateReservationDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.Reservation;
import com.booking.project.model.enums.ReservationMethod;
import com.booking.project.model.enums.ReservationStatus;
import com.booking.project.service.interfaces.IAccommodationService;
import com.booking.project.service.interfaces.IReservationService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private IAccommodationService accommodationService;

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Collection<Reservation>> getReservations(){
//        Collection<Reservation> reservations = reservationService.findAll();
//        return new ResponseEntity<Collection<Reservation>>(reservations, HttpStatus.OK);
//    }

//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Optional<Reservation>> getReservation(@PathVariable("id") Long id){
//        Optional<Reservation> reservation = reservationService.findById(id);
//        if(reservation.isEmpty()){
//            return new ResponseEntity<Optional<Reservation>>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<Optional<Reservation>>(reservation, HttpStatus.OK);
//    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> filterReservations(@PathParam("title") String title,
                                                                    @PathParam("startDate") LocalDate startDate,
                                                                    @PathParam("endDate") LocalDate endDate,
                                                                    @PathParam("status") ReservationStatus status){
        List<Reservation> reservations = reservationService.filter(title, startDate, endDate, status);
        if(reservations.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Reservation>>(reservations, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> createReservation(@RequestBody CreateReservationDTO createReservationDTO) throws Exception {
        List<Object> reservationResponse = accommodationService.reservate(createReservationDTO.getAccommodationId(), createReservationDTO.getStartDate(), createReservationDTO.getEndDate(), createReservationDTO.getNumberOfGuests());

        if(reservationResponse.size() == 1) return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);

        double price = (double) reservationResponse.get(1);
        ReservationMethod reservationMethod = (ReservationMethod) reservationResponse.get(2);
        Reservation createdReservation = reservationService.create(createReservationDTO, price, reservationMethod);
        return new ResponseEntity<Reservation>(createdReservation, HttpStatus.CREATED);
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
