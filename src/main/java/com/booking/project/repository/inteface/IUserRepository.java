package com.booking.project.repository.inteface;

import com.booking.project.model.Accommodation;
import com.booking.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;

public interface IUserRepository extends JpaRepository<User, Long> {
    Collection<User> findByIsReportedTrue();
}
