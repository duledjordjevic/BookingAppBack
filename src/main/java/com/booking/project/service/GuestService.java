package com.booking.project.service;

import com.booking.project.model.Guest;
import com.booking.project.repository.inteface.IGuestRepository;
import com.booking.project.service.interfaces.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class GuestService implements IGuestService {
    @Autowired
    private IGuestRepository repository;
    @Override
    public Collection<Guest> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Guest> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Guest save(Guest guest) throws Exception {
        return repository.save(guest);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
