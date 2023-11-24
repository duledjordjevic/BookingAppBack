package com.booking.project.repository.inteface;

import com.booking.project.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHostRepository extends JpaRepository<Host,Long> {
}
