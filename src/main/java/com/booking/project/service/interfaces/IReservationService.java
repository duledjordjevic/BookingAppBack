package com.booking.project.service.interfaces;

import com.booking.project.model.Reservation;

import java.util.Collection;
import java.util.Optional;

public interface IReservationService {
    Collection<Reservation> findAll();

    Optional<Reservation> findById(Long id);

    Reservation save(Reservation reservation) throws Exception;

    void deleteById(Long id);
}
