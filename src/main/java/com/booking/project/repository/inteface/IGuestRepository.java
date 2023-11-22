package com.booking.project.repository.inteface;

import com.booking.project.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IGuestRepository extends JpaRepository<Guest, Long>{
}
