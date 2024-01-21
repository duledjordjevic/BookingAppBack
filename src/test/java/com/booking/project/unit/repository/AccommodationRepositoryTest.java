package com.booking.project.unit.repository;

import com.booking.project.model.PriceList;
import com.booking.project.model.enums.AccommodationStatus;
import com.booking.project.repository.inteface.IAccommodationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class AccommodationRepositoryTest {

    @Autowired
    private IAccommodationRepository accommodationRepository;

    @Test
    public void should_return_pricelist(){

        List<PriceList> priceLists = accommodationRepository.findPriceList(1L,AccommodationStatus.AVAILABLE);
        assertEquals(7, priceLists.size());
    }
}
