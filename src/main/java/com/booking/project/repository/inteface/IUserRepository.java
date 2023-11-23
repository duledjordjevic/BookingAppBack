package com.booking.project.repository.inteface;

import com.booking.project.model.Accommodation;
import com.booking.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface IUserRepository extends JpaRepository<User, Long> {

}
