package com.booking.project.service.interfaces;

import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.dto.AccommodationDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.enums.AccommodationStatus;
import com.booking.project.model.enums.Amenities;
import com.booking.project.model.enums.ReservationMethod;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public interface IAccommodationService {
    Collection<AccommodationDTO> findAll();
    Collection<AccommodationCardDTO> findAllCards();
    Optional<AccommodationDTO> changeAccommodations(AccommodationDTO accommodationDTO,Long id) throws Exception;

    Optional<Accommodation> findById(Long id);

    Accommodation save(Accommodation accommodation) throws Exception;

    List<Object> reservate(Long accommodationId, LocalDate startDate, LocalDate endDate, int numberOfGuests) throws Exception;


    Boolean changePriceList(LocalDate startDate, LocalDate endDate, Long id, AccommodationStatus accommodationStatus) throws Exception;

    void deleteById(Long id);

    AccommodationDTO changeAvailableStatus(Long id, Boolean isAvailable) throws Exception;
    Collection<Accommodation> findAccomodationsByHostId(Long id);
    Collection<AccommodationCardDTO> filterAccommodations(LocalDate startDate, LocalDate endDate, Integer numOfGuests, String city, Integer startPrice, Integer endPrice);
    AccommodationDTO findAccommodationsDetails(Long id) throws IOException;

    Collection<AccommodationCardDTO> findAccommodationsNotAvailableForReservation() throws IOException;

    AccommodationDTO changeAccommodationReservationMethod(Long id, ReservationMethod reservationMethod) throws Exception;

    AccommodationDTO saveImages(String images, Long accommodationId) throws Exception;

    String getImages(Long accommodationId);
}
