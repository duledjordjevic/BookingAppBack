package com.booking.project.controller;

import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.model.Guest;
import com.booking.project.model.User;
import com.booking.project.service.interfaces.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    @Autowired
    private IGuestService guestService;
    @PreAuthorize("hasRole('GUEST')")
    @GetMapping(value = "/{id}/favourites", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AccommodationCardDTO>> getFavouritesAccommodations(@PathVariable("id") Long id){
        Collection<AccommodationCardDTO> accommodationCardDTOS = guestService.findFavourites(id);

        return new ResponseEntity<Collection<AccommodationCardDTO>>(accommodationCardDTOS, HttpStatus.OK);
    }


}
