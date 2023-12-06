package com.booking.project.service.interfaces;

import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.dto.GuestDTO;
import com.booking.project.model.Guest;

import java.util.Collection;
import java.util.Optional;

public interface IGuestService {
    Collection<Guest> findAll();

    Optional<Guest> findById(Long id);

    Guest findByUser(Long id);

    Guest save(Guest guest) throws Exception;

    Guest addNumberOfCancellation(Long id);

    void deleteById(Long id);
    Guest update(GuestDTO guestDTO, Long id) throws Exception;
    GuestDTO addGuest(GuestDTO guestDTO) throws Exception;
    Collection<AccommodationCardDTO> findFavourites(Long id);
}
