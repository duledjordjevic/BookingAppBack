package com.booking.project.repository.inteface;

import com.booking.project.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccommodationRepository extends JpaRepository<Accommodation, Long> {
}
