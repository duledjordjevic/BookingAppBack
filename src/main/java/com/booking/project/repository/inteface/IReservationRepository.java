package com.booking.project.repository.inteface;

import com.booking.project.model.Reservation;
import com.booking.project.model.enums.ReservationStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Transactional
    @Query("update Reservation r " +
            "set r.status = :status " +
            "where r.guest.user.id = :id and r.startDate > CURRENT_DATE ")
    void cancellGuestReservation(@Param("id") Long id,
                                 @Param("status")ReservationStatus status);

    @Query("SELECT r " +
            "FROM Reservation  r " +
            "JOIN FETCH r.accommodation a " +
            "WHERE (:startDate <= r.endDate AND :endDate >= r.startDate) " +
            "AND a.id = :accommodationId " +
            " AND r.status = :reservationStatus")
    List<Reservation> getOverlaps(@Param("startDate") LocalDate startDate,
                                  @Param("endDate")  LocalDate endDate,
                                  @Param("accommodationId") Long accommodationId,
                                  @Param("reservationStatus") ReservationStatus reservationStatus);
}
