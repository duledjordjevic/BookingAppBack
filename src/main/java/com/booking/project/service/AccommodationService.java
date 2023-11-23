package com.booking.project.service;

import com.booking.project.model.Accommodation;
import com.booking.project.repository.inteface.IAccommodationRepository;
import com.booking.project.service.interfaces.IAccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AccommodationService implements IAccommodationService {
    @Autowired
    private IAccommodationRepository accommodationRepository;
    @Override
    public Collection<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public  Accommodation save(Accommodation accommodation) throws Exception {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }
}


