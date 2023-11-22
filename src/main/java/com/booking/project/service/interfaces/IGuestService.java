package com.booking.project.service.interfaces;

import com.booking.project.model.Guest;

import java.util.Collection;
import java.util.Optional;

public interface IGuestService {
    Collection<Guest> findAll();

    Optional<Guest> find(Long id);

    Guest create(Guest guest) throws Exception;

    Guest update(Guest guest) throws Exception;

    void delete(Long id);
}
