package com.booking.project.service;

import com.booking.project.model.Accomodation;
import com.booking.project.repository.inteface.IAccomodationRepository;
import com.booking.project.service.interfaces.IAccomodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class AccomodationService implements IAccomodationService {
    @Autowired
    private IAccomodationRepository accomodationRepository;

    @Override
    public Collection<Accomodation> findAll() {
        return accomodationRepository.findAll();
    }

    @Override
    public Accomodation find(Long id) {
        return accomodationRepository.find(id);
    }

    @Override
    public Accomodation create(Accomodation accomodation) throws Exception {
        return accomodationRepository.create(accomodation);
    }

    @Override
    public Accomodation update(Accomodation accomodation) throws Exception {
        return accomodationRepository.update(accomodation);
    }

    @Override
    public void delete(Long id) {
        accomodationRepository.delete(id);
    }
}
