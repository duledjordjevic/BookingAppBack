package com.booking.project.service.interfaces;

import com.booking.project.model.Accomodation;


import java.util.Collection;
import java.util.Optional;

public interface IAccomodationService {
    Collection<Accomodation> findAll();

    Optional<Accomodation> findById(Long id);

    Accomodation save(Accomodation accomodation) throws Exception;

    void deleteById(Long id);

}
