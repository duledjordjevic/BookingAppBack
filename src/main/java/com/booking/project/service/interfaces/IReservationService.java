package com.booking.project.service.interfaces;

import com.booking.project.dto.CreateReservationDTO;
import com.booking.project.dto.ReservationDTO;
import com.booking.project.model.Guest;
import com.booking.project.model.Reservation;
import com.booking.project.model.enums.ReservationMethod;
import com.booking.project.model.enums.ReservationStatus;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IReservationService {
    Collection<Reservation> findAll();

    Optional<Reservation> findById(Long id);

    Reservation save(Reservation reservation) throws Exception;

    Boolean deleteById(Long id);
    Collection<Reservation> findByGuestId(Long id);

    Reservation create(CreateReservationDTO createReservationDTO, double price, ReservationMethod reservationMethod) throws Exception;

    List<ReservationDTO> filter(String title, LocalDate startDate, LocalDate endDate, ReservationStatus reservationStatus);

    Reservation updateStatus(Long id, ReservationStatus reservationStatus) throws Exception;

    Reservation cancelReservation(Long id);

    Collection<Reservation> findByHostId(Long id);
}
