package com.booking.project.repository.inteface;

import com.booking.project.model.Accomodation;
import com.booking.project.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface IAccomodationRepository extends JpaRepository<Accomodation, Long> {
}
