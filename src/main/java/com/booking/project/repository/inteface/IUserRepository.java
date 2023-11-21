package com.booking.project.repository.inteface;

import com.booking.project.model.User;

import java.util.Collection;

public interface IUserRepository {

    Collection<User> findAll();

    User find(Long id);

    User create(User greeting) throws Exception;

    User update(User greeting) throws Exception;

    void delete(Long id);
}
