package com.booking.project.service;

import com.booking.project.model.Reservation;
import com.booking.project.repository.inteface.IReservationRepository;
import com.booking.project.service.interfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;
    @Override
    public Collection<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation save(Reservation reservation) throws Exception {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
