package com.booking.project.controller;

import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.dto.AccommodationDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.Host;
import com.booking.project.model.enums.ReservationMethod;
import com.booking.project.service.interfaces.IAccommodationService;
import com.booking.project.service.interfaces.IHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
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
        LocalDate specificDate = LocalDate.of(2023, 11, 17);
        LocalDate specificDate2 = LocalDate.of(2023, 11, 27);
        String loc = "Beograd";
        int num = 5;
        Collection<AccommodationDTO> accommodations1 = accommodationService.filterAccommodations(specificDate,specificDate2,num, loc);
        Collection<AccommodationDTO> accommodations = accommodationService.findAll();
        return new ResponseEntity<Collection<AccommodationDTO>>(accommodations, HttpStatus.OK);
    }
    @GetMapping(value = "/cards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccommodationsCards(){
        Collection<AccommodationCardDTO> accommodations = accommodationService.findAllCards();
        return new ResponseEntity<Collection<AccommodationCardDTO>>(accommodations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccommodationDetails(@PathVariable("id") Long id){
        AccommodationDTO accommodationDTO = accommodationService.findAccommodationsDetails(id);
        if(accommodationDTO == null)    return new ResponseEntity<AccommodationDTO>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<AccommodationDTO>(accommodationDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccommodation(@RequestBody AccommodationDTO accommodationDTO) throws Exception {
        Accommodation accommodationNew = new Accommodation(accommodationDTO);
        Host host = hostService.findById(accommodationDTO.getHostId()).get();
        accommodationNew.setHost(host);
        Accommodation savedAccommodation = accommodationService.save(accommodationNew);
        return new ResponseEntity<Accommodation>(savedAccommodation, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Accommodation> deleteAccommodation(@PathVariable("id") Long id){
        accommodationService.deleteById(id);
        return new ResponseEntity<Accommodation>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value ="/{id}/isAvailable/{isAvailable}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeAccommodationAvailableStatus(@PathVariable Long id,@PathVariable Boolean isAvailable) throws Exception {
        AccommodationDTO accommodationDTO = accommodationService.changeAvailableStatus(id,isAvailable);

        if(accommodationDTO == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<AccommodationDTO>(accommodationDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/host/{id_host}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHostAccommodations(@PathVariable("id_host") Long id){
        Collection<Accommodation> accommodations = accommodationService.findAccomodationsByHostId(id);
        return new ResponseEntity<Collection<Accommodation>>(accommodations, HttpStatus.OK);
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAccommodation(@RequestBody AccommodationDTO accommodationDTO, @PathVariable Long id) throws Exception{
        Optional<AccommodationDTO> accommodation = accommodationService.changeAccommodations(accommodationDTO,id);

        if (accommodation.get() == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<AccommodationDTO>(accommodation.get(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/cards/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> filterAccommodations(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer numberOfGuests,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate
    ) {
        Collection<AccommodationDTO> accommodations = accommodationService.filterAccommodations(startDate,endDate,numberOfGuests,city);

        return new ResponseEntity<Collection<AccommodationDTO>>(accommodations, HttpStatus.OK);
    }
    @PutMapping(value ="/{id}/reservationMethod/{reservationMethod}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeAccommodationReservationMethod(@PathVariable Long id, @PathVariable ReservationMethod reservationMethod) throws Exception {
        AccommodationDTO accommodationDTO = accommodationService.changeAccommodationReservationMethod(id,reservationMethod);

        if(accommodationDTO == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<AccommodationDTO>(accommodationDTO, HttpStatus.OK);
    }

}
