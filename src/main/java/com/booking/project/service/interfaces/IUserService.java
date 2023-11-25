package com.booking.project.service.interfaces;

import com.booking.project.dto.UserCredentialsDTO;
import com.booking.project.model.Guest;
import com.booking.project.model.User;
import com.booking.project.model.enums.UserStatus;

import java.util.Collection;
import java.util.Optional;

public interface IUserService {

    Collection<User> findAll();

    Optional<User> findById(Long id);

    User save(User user) throws Exception;

    void deleteById(Long id);

    User update(UserCredentialsDTO userCredentialsDTO, Long id) throws Exception;

    User changeStatus(Long id, UserStatus status) throws Exception;
}
