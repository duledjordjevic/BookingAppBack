package com.booking.project.service;

import com.booking.project.model.User;
import com.booking.project.repository.UserRepository;
import com.booking.project.repository.inteface.IUserRepository;
import com.booking.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User find(Long id) {
        return userRepository.find(id);
    }

    @Override
    public User create(User greeting) throws Exception {
        return userRepository.create(greeting);
    }

    @Override
    public User update(User greeting) throws Exception {
        return userRepository.update(greeting);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
