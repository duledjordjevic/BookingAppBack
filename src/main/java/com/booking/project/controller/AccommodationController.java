package com.booking.project.controller;

import com.booking.project.dto.AccommodationApprovalStatusDTO;
import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.dto.AccommodationDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.Host;
import com.booking.project.model.enums.AccommodationApprovalStatus;
import com.booking.project.model.enums.AccommodationType;
import com.booking.project.model.enums.Amenities;
import com.booking.project.model.enums.ReservationMethod;
import com.booking.project.service.interfaces.IAccommodationService;
import com.booking.project.service.interfaces.IHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Optional;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    @Autowired
    private IAccommodationService accommodationService;
    @Autowired
    private IHostService hostService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccommodations(){
        Collection<AccommodationDTO> accommodations = accommodationService.findAll();
        return new ResponseEntity<Collection<AccommodationDTO>>(accommodations, HttpStatus.OK);
    }
    @GetMapping(value = "/cards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccommodationsCards(){
        Collection<AccommodationCardDTO> accommodations = accommodationService.findAllCards();
        return new ResponseEntity<Collection<AccommodationCardDTO>>(accommodations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccommodationDetails(@PathVariable("id") Long id) throws IOException {
        AccommodationDTO accommodationDTO = accommodationService.findAccommodationsDetails(id);
        if(accommodationDTO == null)    return new ResponseEntity<AccommodationDTO>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<AccommodationDTO>(accommodationDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/adminApproving", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccommodationsForApproving() throws IOException {
        Collection<AccommodationCardDTO> accommodationCards = accommodationService.findApprovalPendingAccommodations();
        if(accommodationCards == null)    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Collection<AccommodationCardDTO>>(accommodationCards, HttpStatus.OK);
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPopularAccommodations() throws IOException {
        Collection<AccommodationCardDTO> accommodationCards = accommodationService.findPopularAccommodations();
        if(accommodationCards == null)    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Collection<AccommodationCardDTO>>(accommodationCards, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('HOST')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccommodation(@RequestBody AccommodationDTO accommodationDTO) throws Exception {
        Accommodation accommodationNew = new Accommodation(accommodationDTO);
        Host host = hostService.findById(accommodationDTO.getHostId()).get();
        accommodationNew.setHost(host);
        Accommodation savedAccommodation = accommodationService.save(accommodationNew);
        return new ResponseEntity<Accommodation>(savedAccommodation, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('HOST')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Accommodation> deleteAccommodation(@PathVariable("id") Long id){
        accommodationService.deleteById(id);
        return new ResponseEntity<Accommodation>(HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value ="/{id}/approvalStatus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeAccommodationAvailableStatus(@PathVariable Long id, @RequestBody AccommodationApprovalStatusDTO approvalStatus) throws Exception {
        AccommodationDTO accommodationDTO = accommodationService.changeAvailableStatus(id, approvalStatus.getApprovalStatus());

        if(accommodationDTO == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<AccommodationDTO>(accommodationDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('HOST')")
    @GetMapping(value = "/host/{id_host}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHostAccommodations(@PathVariable("id_host") Long id){
        Collection<Accommodation> accommodations = accommodationService.findAccomodationsByHostId(id);
        return new ResponseEntity<Collection<Accommodation>>(accommodations, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('HOST')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAccommodation(@RequestBody AccommodationDTO accommodationDTO, @PathVariable Long id) throws Exception{
        Optional<AccommodationDTO> accommodation = accommodationService.changeAccommodations(accommodationDTO,id);

        if (accommodation.get() == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<AccommodationDTO>(accommodation.get(), HttpStatus.CREATED);
    }
//    @PreAuthorize("hasRole('HOST') OR hasRole('GUEST')")
    @GetMapping(value = "/cards/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> filterAccommodations(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer numberOfGuests,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate,
            @RequestParam(required = false) Integer startPrice,
            @RequestParam(required = false) Integer endPrice,
            @RequestParam(required = false) Collection<Amenities> amenities,
            @RequestParam(required = false) AccommodationType accommodationType
    ) throws IOException {
        Collection<AccommodationCardDTO> accommodations = accommodationService.filterAccommodations(startDate,endDate,numberOfGuests,city,startPrice,endPrice,amenities,accommodationType);

        return new ResponseEntity<Collection<AccommodationCardDTO>>(accommodations, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('HOST')")
    @PutMapping(value ="/{id}/reservationMethod/{reservationMethod}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeAccommodationReservationMethod(@PathVariable Long id, @PathVariable ReservationMethod reservationMethod) throws Exception {
        AccommodationDTO accommodationDTO = accommodationService.changeAccommodationReservationMethod(id,reservationMethod);

        if(accommodationDTO == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<AccommodationDTO>(accommodationDTO, HttpStatus.OK);
    }
    @GetMapping(value="/minMaxPrice",produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> getMinMaxPrices(){
        Collection<Double> minMax = accommodationService.getMinMaxPrice();
        return new ResponseEntity<Collection<Double>>(minMax, HttpStatus.OK);
    }

}
