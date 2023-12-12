package com.booking.project.service.interfaces;

import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.dto.AccommodationDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.enums.AccommodationApprovalStatus;
import com.booking.project.model.enums.AccommodationStatus;
import com.booking.project.model.enums.Amenities;
import com.booking.project.model.enums.ReservationMethod;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public interface IAccommodationService {
    Collection<AccommodationDTO> findAll();
    Collection<AccommodationCardDTO> findAllCards();
    Optional<AccommodationDTO> changeAccommodations(AccommodationDTO accommodationDTO,Long id) throws Exception;

    Optional<Accommodation> findById(Long id);

    Accommodation save(Accommodation accommodation) throws Exception;

    List<Object> reservate(Long accommodationId, LocalDate startDate, LocalDate endDate, int numberOfGuests) throws Exception;

    ArrayList<Object> calculateReservationPrice(LocalDate startDate, LocalDate endDate, Long id, int numberOfGuests);

    Boolean changePriceList(LocalDate startDate, LocalDate endDate, Long id, AccommodationStatus accommodationStatus) throws Exception;

    void deleteById(Long id);

    AccommodationDTO changeAvailableStatus(Long id, AccommodationApprovalStatus approvalStatus) throws Exception;
    Collection<Accommodation> findAccomodationsByHostId(Long id);
    Collection<AccommodationCardDTO> filterAccommodations(LocalDate startDate, LocalDate endDate, Integer numOfGuests, String city, Integer startPrice, Integer endPrice,Collection<Amenities> amenities);
    AccommodationDTO findAccommodationsDetails(Long id) throws IOException;

    Collection<AccommodationCardDTO> findApprovalPendingAccommodations() throws IOException;

    AccommodationDTO changeAccommodationReservationMethod(Long id, ReservationMethod reservationMethod) throws Exception;

    AccommodationDTO saveImages(String images, Long accommodationId) throws Exception;

    String getImages(Long accommodationId);
}
