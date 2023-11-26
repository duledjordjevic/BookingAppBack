package com.booking.project.repository.inteface;

import com.booking.project.model.Accommodation;
import com.booking.project.model.enums.AccommodationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface IAccommodationRepository extends JpaRepository<Accommodation, Long> {
    Collection<Accommodation> findAccommodationsByHostId(Long id);
    @Query("SELECT a " +
            "FROM Accommodation a " +
            "JOIN a.prices p " +
            "WHERE (p.date BETWEEN :startDate AND :endDate) " +
            "   AND (:numOfGuests IS NULL OR (a.minGuests <= :numOfGuests AND a.maxGuests >= :numOfGuests)) " +
            "   AND (:location IS NULL OR a.address.city = :location) " +
            "GROUP BY a.id " +
            "HAVING COUNT(CASE WHEN p.status = 'AVAILABLE' THEN 1 ELSE NULL END) = COUNT(p.id)")
    Collection<Accommodation> filterAccommodations(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("location") String location,
            @Param("numOfGuests") Integer numOfGuests
    );

}
