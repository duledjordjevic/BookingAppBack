package com.booking.project.service.interfaces;

import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.dto.AccommodationDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.PriceList;
import com.booking.project.model.enums.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public interface IAccommodationService {
    Collection<AccommodationDTO> findAll();
    Collection<AccommodationCardDTO> findAllCards() throws IOException;
    Optional<AccommodationDTO> changeAccommodations(AccommodationDTO accommodationDTO,Long id) throws Exception;

    Optional<Accommodation> findById(Long id);

    Accommodation save(Accommodation accommodation) throws Exception;

    List<Object> reservate(Long accommodationId, LocalDate startDate, LocalDate endDate, int numberOfGuests) throws Exception;

    ArrayList<Object> calculateReservationPrice(LocalDate startDate, LocalDate endDate, Long id, int numberOfGuests);

    Boolean changePriceList(LocalDate startDate, LocalDate endDate, Long id, AccommodationStatus accommodationStatus) throws Exception;

    List<LocalDate> getAvailableDates(Long id);

    void deleteById(Long id);

    AccommodationDTO changeAvailableStatus(Long id, AccommodationApprovalStatus approvalStatus) throws Exception;
    Collection<AccommodationCardDTO> findAccomodationsByHostId(Long id) throws IOException;
    Collection<AccommodationCardDTO> filterAccommodations(LocalDate startDate, LocalDate endDate, Integer numOfGuests, String city, Integer startPrice, Integer endPrice, Collection<Amenities> amenities, AccommodationType accommodationType) throws IOException;
    AccommodationDTO findAccommodationsDetails(Long id) throws IOException;

    Collection<AccommodationCardDTO> findApprovalPendingAccommodations() throws IOException;
    Collection<AccommodationCardDTO> findPopularAccommodations() throws IOException;

    AccommodationDTO changeAccommodationReservationMethod(Long id, ReservationMethod reservationMethod) throws Exception;

    AccommodationDTO saveImages(String images, Long accommodationId) throws Exception;

    String getImages(Long accommodationId);

    Collection<Double> getMinMaxPrice();

    List<PriceList> findPriceList(Long id);
    List<AccommodationDTO> getGuestAccommodations(Long guestUserId);
    List<AccommodationDTO> getGuestAccommodationsForComment(Long guestUserId) throws IOException;
}
