package com.booking.project.service.interfaces;

import com.booking.project.model.User;

import java.util.Collection;

public interface IUserService {

    Collection<User> findAll();

    User find(Long id);

    User create(User greeting) throws Exception;

    User update(User greeting) throws Exception;

    void delete(Long id);
}
