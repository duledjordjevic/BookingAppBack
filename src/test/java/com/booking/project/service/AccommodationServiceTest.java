package com.booking.project.service;

import com.booking.project.model.Accommodation;
import com.booking.project.model.PriceList;
import com.booking.project.model.enums.*;
import com.booking.project.repository.inteface.IAccommodationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class AccommodationServiceTest {
    @Autowired
    private AccommodationService accommodationService;
    @MockBean
    private IAccommodationRepository accommodationRepository;

    @Test
    void test_reservate_when_accommodation_not_found() throws Exception {
        when(accommodationRepository.findById(10L)).thenReturn(Optional.empty());

        List<Object> result =  accommodationService.reservate(10L, LocalDate.now(),LocalDate.now(),5);

        assertFalse((boolean) result.get(0));
        assertEquals(1,result.size());
        verify(accommodationRepository).findById(10L);
        verifyNoMoreInteractions(accommodationRepository);
    }

    @ParameterizedTest
    @MethodSource(value = "sourceList_approval_status_and_num_guests")
    void test_reservate_when_invalid_acc_data(AccommodationApprovalStatus approvalStatus,int numOfGuests) throws Exception {
        Accommodation accommodation = new Accommodation(10L,"Accommodation",null,null,null,1,10,
                AccommodationType.HOTEL, CancellationPolicy.HOURS72, approvalStatus, ReservationMethod.AUTOMATIC,
                true,null,null,null);
        when(accommodationRepository.findById(10L)).thenReturn(Optional.of(accommodation));

        List<Object> result = accommodationService.reservate(10L,LocalDate.now(),LocalDate.now(),numOfGuests);

        assertFalse((boolean) result.get(0));
        assertEquals(1,result.size());
        verify(accommodationRepository).findById(10L);
        verifyNoMoreInteractions(accommodationRepository);
    }


    static List<Arguments> sourceList_approval_status_and_num_guests() {
        return Arrays.asList(arguments(AccommodationApprovalStatus.PENDING, 5),
                arguments(AccommodationApprovalStatus.APPROVED, 15),
                arguments(AccommodationApprovalStatus.PENDING, 15),
                arguments(AccommodationApprovalStatus.DECLINED,5));
    }

    @ParameterizedTest
    @MethodSource(value = "source_price_list_not_available")
    void test_reservate_when_acc_not_avialable_for_date_interval(Set<PriceList> priceList) throws Exception {
        Accommodation accommodation = new Accommodation(10L,"Accommodation",null,null,null,1,10,
                AccommodationType.HOTEL, CancellationPolicy.HOURS72, AccommodationApprovalStatus.APPROVED, ReservationMethod.AUTOMATIC,
                true,priceList,null,null);
        when(accommodationRepository.findById(10L)).thenReturn(Optional.of(accommodation));

        List<Object> result = accommodationService.reservate(10L,LocalDate.of(2024, 2, 2),
                LocalDate.of(2024, 2, 4),5);

        assertFalse((boolean) result.get(0));
        assertEquals(1,result.size());
        verify(accommodationRepository).findById(10L);
        verifyNoMoreInteractions(accommodationRepository);


    }
    static List<Arguments> source_price_list_not_available() {
        return List.of(arguments(
                createPriceListSet(new PriceList
                        (1L, LocalDate.of(2024, 2, 1), 100, AccommodationStatus.AVAILABLE),
                        new PriceList
                                (2L,LocalDate.of(2024, 2, 2),100, AccommodationStatus.AVAILABLE),
                        new PriceList
                                (2L,LocalDate.of(2024, 2, 3),100, AccommodationStatus.NOT_AVAILABLE),
                        new PriceList
                                (2L,LocalDate.of(2024, 2, 4),100, AccommodationStatus.RESERVED)
                        )
                )
        );
    }
    @ParameterizedTest
    @MethodSource(value = "source_price_list_available")
    void test_reservate_succesfully(Set<PriceList> priceList) throws Exception {
        Accommodation accommodation = new Accommodation(10L,"Accommodation",null,null,null,1,10,
                AccommodationType.HOTEL, CancellationPolicy.HOURS72, AccommodationApprovalStatus.APPROVED, ReservationMethod.AUTOMATIC,
                true,priceList,null,null);
        when(accommodationRepository.findById(10L)).thenReturn(Optional.of(accommodation));
        when(accommodationRepository.save(accommodation)).thenReturn(null);

        List<Object> result = accommodationService.reservate(10L,LocalDate.of(2024, 2, 2),
                LocalDate.of(2024, 2, 4),5);

        assertEquals(3,result.size());
        assertEquals(ReservationMethod.AUTOMATIC,result.get(2));
        verify(accommodationRepository).findById(10L);
        verify(accommodationRepository).save(accommodation);

    }
    static List<Arguments> source_price_list_available() {
        return List.of(arguments(
                        createPriceListSet(new PriceList
                                        (1L, LocalDate.of(2024, 2, 1), 100, AccommodationStatus.AVAILABLE),
                                new PriceList
                                        (2L,LocalDate.of(2024, 2, 2),100, AccommodationStatus.AVAILABLE),
                                new PriceList
                                        (2L,LocalDate.of(2024, 2, 3),100, AccommodationStatus.AVAILABLE),
                                new PriceList
                                        (2L,LocalDate.of(2024, 2, 4),100, AccommodationStatus.AVAILABLE)
                        )
                )
        );
    }
    private static Set<PriceList> createPriceListSet(PriceList... priceLists) {
        return new HashSet<>(Set.of(priceLists));
    }

}