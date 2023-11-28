package com.booking.project.repository.inteface;

import com.booking.project.model.Accommodation;
import com.booking.project.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;

public interface IGuestRepository extends JpaRepository<Guest, Long>{
    @Query(
            "SELECT g.favourites " +
                    "FROM Guest g " +
                    "where g.id = :guestId "
    )
    public Collection<Accommodation> findByFavourites(
            @Param("guestId") Long id);
}
