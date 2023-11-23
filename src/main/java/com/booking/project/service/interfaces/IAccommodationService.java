package com.booking.project.service.interfaces;

import com.booking.project.model.Accommodation;
import java.util.Collection;
import java.util.Optional;

public interface IAccommodationService {
    Collection<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Accommodation save(Accommodation accommodation) throws Exception;

    void deleteById(Long id);

}
