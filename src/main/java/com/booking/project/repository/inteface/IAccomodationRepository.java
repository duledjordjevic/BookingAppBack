package com.booking.project.repository.inteface;

import com.booking.project.model.Accomodation;

import java.util.Collection;

public interface IAccomodationRepository {
    Collection<Accomodation> findAll();

    Accomodation find(Long id);

    Accomodation create(Accomodation accomodation) throws Exception;

    Accomodation update(Accomodation accomodation) throws Exception;

    void delete(Long id);
}
