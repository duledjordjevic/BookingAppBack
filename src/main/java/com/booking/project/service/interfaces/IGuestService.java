package com.booking.project.service.interfaces;

import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.dto.GuestDTO;
import com.booking.project.model.Guest;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface IGuestService {
    Collection<Guest> findAll();

    Optional<Guest> findById(Long id);

    Optional<Guest> findByUser(Long id);

    Guest save(Guest guest) throws Exception;

    Guest addNumberOfCancellation(Long id);

    void deleteById(Long id);
    Guest update(GuestDTO guestDTO, Long id) throws Exception;
    GuestDTO addGuest(GuestDTO guestDTO) throws Exception;
    Collection<AccommodationCardDTO> findFavourites(Long id) throws IOException;

    boolean addFavourite(Long accommodationId, Long guestUserId) throws Exception;

    boolean isFavourite(Long accommodationId, Long guestUserId) throws Exception;

    boolean removeFavourite(Long accommodationId, Long guestUserId) throws Exception;
}
