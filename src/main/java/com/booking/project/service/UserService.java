package com.booking.project.service;

import com.booking.project.model.User;
import com.booking.project.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements IUserService {
    @Override
    public Collection<User> findAll() {
        return null;
    }

    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public User create(User greeting) throws Exception {
        return null;
    }

    @Override
    public User update(User greeting) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
