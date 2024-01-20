package com.booking.project.repository;

import com.booking.project.model.*;
import com.booking.project.model.enums.*;
import com.booking.project.repository.inteface.IReservationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ReservationRepositoryTest {

    @Autowired
    private IReservationRepository reservationRepository;

    @Test
    @DisplayName("Test")
    public void should_return_overlap_reservations_through_sql_file(){

        List<Reservation> overlapReservations = reservationRepository.getOverlaps(LocalDate.parse("2024-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2024-01-24", DateTimeFormatter.ofPattern("yyyy-MM-dd")), 1L, ReservationStatus.PENDING);

        assertEquals(2, overlapReservations.size());
    }
}
