package com.booking.project.repository;

import com.booking.project.model.Accomodation;
import com.booking.project.repository.inteface.IAccomodationRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public class AccomodationRepository implements IAccomodationRepository {
    private List<Accomodation> accomodations;
    @Override
    public Collection<Accomodation> findAll() {
        return accomodations;
    }

    @Override
    public Accomodation find(Long id) {
        for (Accomodation accomodation : accomodations) {
            if (accomodation.getId().equals(id)) {
                return accomodation;
            }
        }
        return null;
    }

    @Override
    public Accomodation create(Accomodation accomodation) throws Exception {
        return accomodation;
    }

    @Override
    public Accomodation update(Accomodation accomodation) throws Exception {
        return accomodation;
    }

    @Override
    public void delete(Long id) {
        accomodations.removeIf(accomodation -> accomodation.getId().equals(id));
    }
}
