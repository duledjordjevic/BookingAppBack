package com.booking.project.repository.inteface;

import com.booking.project.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}
