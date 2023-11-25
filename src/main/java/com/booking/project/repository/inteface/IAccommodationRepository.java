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
            "WHERE p.date BETWEEN :startDate AND :endDate " +
            "GROUP BY a.id " +
            "HAVING COUNT(CASE WHEN p.status = 'AVAILABLE' THEN 1 ELSE NULL END) = COUNT(p.id)")
    Collection<Accommodation> filterAccommodations(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    @Query("SELECT a " +
            "FROM Accommodation a " +
            "WHERE a.address.city = :location " +
            "GROUP BY a.id")
    List<Accommodation> findAccommodationsByCity(
            @Param("location") String location
    );
    @Query("SELECT a " +
            "FROM Accommodation a " +
            "WHERE a.minGuests <= :numOfGuests AND a.maxGuests >= :numOfGuests " +
            "GROUP BY a.id")
    List<Accommodation> findAccommodationsByGuests(
            @Param("numOfGuests") Integer numOfGuests
    );

}
