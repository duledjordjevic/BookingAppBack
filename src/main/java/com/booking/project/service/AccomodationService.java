package com.booking.project.service;

import com.booking.project.model.Accomodation;
import com.booking.project.repository.inteface.IAccomodationRepository;
import com.booking.project.service.interfaces.IAccomodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AccomodationService implements IAccomodationService {
    @Autowired
    private IAccomodationRepository accomodationRepository;
    @Override
    public Collection<Accomodation> findAll() {
        return accomodationRepository.findAll();
    }

    @Override
    public Optional<Accomodation> findById(Long id) {
        return accomodationRepository.findById(id);
    }

    @Override
    public Accomodation save(Accomodation accomodation) throws Exception {
        return accomodationRepository.save(accomodation);
    }

    @Override
    public void deleteById(Long id) {
        accomodationRepository.deleteById(id);
    }
}

