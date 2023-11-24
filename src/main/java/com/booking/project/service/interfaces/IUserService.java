package com.booking.project.service.interfaces;

import com.booking.project.model.Guest;
import com.booking.project.model.User;

import java.util.Collection;
import java.util.Optional;

public interface IUserService {

    Collection<User> findAll();

    Optional<User> findById(Long id);

    User save(User user) throws Exception;

    void deleteById(Long id);
    void block(Long id);
    void unblock(Long id);
}
