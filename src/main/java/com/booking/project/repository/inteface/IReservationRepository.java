package com.booking.project.repository.inteface;

import com.booking.project.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("Select r " +
            "from Reservation r " +
            "where r.guest.id = :guestId " +
            "and r.status='ACCEPTED' " +
            "AND r.startDate > CURRENT_DATE ")
    Collection<Reservation> findByGuest(@Param("guestId") Long guestId);
    @Query("Select r " +
            "from Reservation r " +
            "where r.accommodation.host.id = :hostId " +
            "and r.status = 'ACCEPTED' " +
            "and r.startDate > CURRENT_DATE ")
    Collection<Reservation> findByHost( @Param("hostId") Long hostId);

}
