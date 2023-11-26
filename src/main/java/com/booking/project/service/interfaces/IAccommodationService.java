package com.booking.project.service.interfaces;

import com.booking.project.model.Accommodation;
import com.booking.project.model.enums.AccomodationStatus;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IAccommodationService {
    Collection<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Accommodation save(Accommodation accommodation) throws Exception;

    List<Object> reservate(Long accommodationId, LocalDate startDate, LocalDate endDate, int numberOfGuests) throws Exception;

    Boolean changePriceList(LocalDate startDate, LocalDate endDate, Long id, AccomodationStatus accomodationStatus) throws Exception;

    void deleteById(Long id);

}
