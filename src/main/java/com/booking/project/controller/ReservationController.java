package com.booking.project.controller;

import com.booking.project.dto.CreateReservationDTO;
import com.booking.project.dto.ReservationDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.Guest;
import com.booking.project.model.Reservation;
import com.booking.project.model.enums.AccomodationStatus;
import com.booking.project.model.enums.ReservationMethod;
import com.booking.project.model.enums.ReservationStatus;
import com.booking.project.service.interfaces.IAccommodationService;
import com.booking.project.service.interfaces.IGuestService;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private IAccommodationService accommodationService;

    @Autowired
    private IGuestService guestService;

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
                                                                    @PathParam("startDate") Date startDate,
                                                                    @PathParam("endDate") Date endDate,
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

    @PutMapping(value = "/{id}/{reservationStatus}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateReservationStatus(@PathVariable Long id, @PathVariable ReservationStatus reservationStatus) throws Exception{
        Reservation reservation = reservationService.updateStatus(id, reservationStatus);

        if (reservation == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ReservationDTO>(new ReservationDTO(reservation), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/pending/{id}")
    public ResponseEntity<?> deletePendingReservation(@PathVariable("id") Long id){
        Boolean isDeleted = reservationService.deleteById(id);

        if(isDeleted.equals(false)){
            return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PutMapping(value = "/deleteAccepted/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable("id") Long id) throws Exception {
        Reservation reservation = reservationService.cancelReservation(id);

        if(reservation == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Guest guest = reservation.getGuest();
        guestService.addNumberOfCancellation(guest.getId());

        accommodationService.changePriceList(reservation.getStartDate(), reservation.getEndDate(), reservation.getAccommodation().getId(), AccomodationStatus.AVAILABLE);

        return new ResponseEntity<>(new ReservationDTO(reservation), HttpStatus.OK);
    }




}
