package com.booking.project.service;

import com.booking.project.model.User;
import com.booking.project.repository.inteface.IUserRepository;
import com.booking.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository repository;
    @Override
    public Collection<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User save(User user) throws Exception {
        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
