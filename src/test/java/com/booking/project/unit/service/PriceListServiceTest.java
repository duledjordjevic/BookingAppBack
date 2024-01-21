package com.booking.project.unit.service;

import com.booking.project.dto.IntervalPriceDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.PriceList;
import com.booking.project.model.enums.*;
import com.booking.project.service.AccommodationService;
import com.booking.project.service.PriceListService;
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
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PriceListServiceTest {
    @Autowired
    private PriceListService priceListService;

    @MockBean
    private AccommodationService accommodationService;

    @ParameterizedTest
    @MethodSource(value = "source_dates_prices_nothing_to_order_one_interval")
    void test_getPriceList_nothing_to_order_one_interval(LocalDate date, double price){

        List<IntervalPriceDTO> intervalPriceDTOS = new ArrayList<>();
        IntervalPriceDTO intervalPriceDTO = new IntervalPriceDTO(LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 2, 1), 1000.0);
        intervalPriceDTOS.add(intervalPriceDTO);


        Set<PriceList> priceLists = priceListService.getPriceList(intervalPriceDTOS);

        // Assert
        assertNotNull(priceLists);
        for (PriceList priceList : priceLists) {
            if(priceList.getDate().equals(date)){
                assertEquals(price, priceList.getPrice());
            }
        }
        assertEquals(32, priceLists.size());


    }

    @ParameterizedTest
    @MethodSource(value = "source_dates_prices_nothing_to_order_more_intervals")
    void test_getPriceList_nothing_to_order_more_intervals(LocalDate date, double price){

        List<IntervalPriceDTO> intervalPriceDTOS = new ArrayList<>();
        IntervalPriceDTO intervalPriceDTO1 = new IntervalPriceDTO(LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 2, 1), 1000.0);
        IntervalPriceDTO intervalPriceDTO2 = new IntervalPriceDTO(LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 4, 1), 2000.0);
        intervalPriceDTOS.add(intervalPriceDTO1);
        intervalPriceDTOS.add(intervalPriceDTO2);

        Set<PriceList> priceLists = priceListService.getPriceList(intervalPriceDTOS);

        // Assert
        assertNotNull(priceLists);
        for (PriceList priceList : priceLists) {
            if(priceList.getDate().equals(date)){
                assertEquals(price, priceList.getPrice());
            }
        }
        assertEquals(64, priceLists.size());
    }

    @ParameterizedTest
    @MethodSource(value = "source_dates_prices_order_one_interval")
    void test_getPriceList_order_one_interval(LocalDate date, double price){

        List<IntervalPriceDTO> intervalPriceDTOS = new ArrayList<>();
        IntervalPriceDTO intervalPriceDTO1 = new IntervalPriceDTO(LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 5), 1000.0);
        IntervalPriceDTO intervalPriceDTO2 = new IntervalPriceDTO(LocalDate.of(2024, 1, 3),
                LocalDate.of(2024, 1, 10), 2000.0);
        intervalPriceDTOS.add(intervalPriceDTO1);
        intervalPriceDTOS.add(intervalPriceDTO2);

        Set<PriceList> priceLists = priceListService.getPriceList(intervalPriceDTOS);

        // Assert
        assertNotNull(priceLists);
        for (PriceList priceList : priceLists) {
            if(priceList.getDate().equals(date)){
                assertEquals(price, priceList.getPrice());
            }
        }
        assertEquals(10, priceLists.size());


    }
    @ParameterizedTest
    @MethodSource(value = "source_dates_prices_order_more_intervals")
    void test_getPriceList_order_more_intervals(LocalDate date, double price){

        List<IntervalPriceDTO> intervalPriceDTOS = new ArrayList<>();
        IntervalPriceDTO intervalPriceDTO1 = new IntervalPriceDTO(LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 5), 1000.0);
        IntervalPriceDTO intervalPriceDTO2 = new IntervalPriceDTO(LocalDate.of(2024, 1, 3),
                LocalDate.of(2024, 1, 10), 2000.0);
        IntervalPriceDTO intervalPriceDTO3 = new IntervalPriceDTO(LocalDate.of(2024, 1, 2),
                LocalDate.of(2024, 1, 10), 3000.0);
        IntervalPriceDTO intervalPriceDTO4 = new IntervalPriceDTO(LocalDate.of(2024, 1, 7),
                LocalDate.of(2024, 1, 15), 4000.0);

        intervalPriceDTOS.add(intervalPriceDTO1);
        intervalPriceDTOS.add(intervalPriceDTO2);
        intervalPriceDTOS.add(intervalPriceDTO3);
        intervalPriceDTOS.add(intervalPriceDTO4);

        Set<PriceList> priceLists = priceListService.getPriceList(intervalPriceDTOS);

        // Assert
        assertNotNull(priceLists);
        for (PriceList priceList : priceLists) {
            if(priceList.getDate().equals(date)){
                assertEquals(price, priceList.getPrice());
            }
        }

        assertEquals(15, priceLists.size());
    }

    @Test
    void test_getIntervalPrices_nothing_to_order_one_interval(){
        // Arrange
        Set<PriceList> prices =  new HashSet<>();
        prices.add(new PriceList(1L, LocalDate.now(), 100, AccommodationStatus.AVAILABLE));
        prices.add(new PriceList(2L, LocalDate.now().plusDays(1), 100, AccommodationStatus.AVAILABLE));
        prices.add(new PriceList(3L, LocalDate.now().plusDays(2), 100, AccommodationStatus.AVAILABLE));
        prices.add(new PriceList(4L, LocalDate.now().plusDays(3), 100, AccommodationStatus.AVAILABLE));
        List<PriceList> listPrices = new ArrayList<>(prices);
        listPrices.sort(Comparator.comparing(PriceList::getId));
        Accommodation accommodation = new Accommodation(1L,"Accommodation",null,null,null,1,10,
                AccommodationType.HOTEL, CancellationPolicy.HOURS72, AccommodationApprovalStatus.APPROVED, ReservationMethod.AUTOMATIC,
                true,prices,null,null);
        when(accommodationService.findPriceList(1L)).thenReturn(listPrices);

        // Act
        List<IntervalPriceDTO> intervalPriceDTOS = priceListService.getIntervalPrices(1L);

        // Assert
        assertNotNull(intervalPriceDTOS);
        assertEquals(1, intervalPriceDTOS.size());

        assertEquals(100.0, intervalPriceDTOS.get(0).getPrice());
        assertEquals(LocalDate.now(), intervalPriceDTOS.get(0).getStartDate());
        assertEquals(LocalDate.now().plusDays(3), intervalPriceDTOS.get(0).getEndDate());

        verify(accommodationService).findPriceList(1L);
        verifyNoMoreInteractions(accommodationService);
    }

    @Test
    void test_getIntervalPrices_nothing_to_order_more_interval(){
        // Arrange
        Set<PriceList> prices =  new HashSet<>();
        prices.add(new PriceList(1L, LocalDate.now(), 100, AccommodationStatus.AVAILABLE));
        prices.add(new PriceList(2L, LocalDate.now().plusDays(1), 100, AccommodationStatus.AVAILABLE));
        prices.add(new PriceList(3L, LocalDate.now().plusDays(9), 200, AccommodationStatus.AVAILABLE));
        prices.add(new PriceList(4L, LocalDate.now().plusDays(10), 200, AccommodationStatus.AVAILABLE));
        List<PriceList> listPrices = new ArrayList<>(prices);
        listPrices.sort(Comparator.comparing(PriceList::getId));
        Accommodation accommodation = new Accommodation(1L,"Accommodation",null,null,null,1,10,
                AccommodationType.HOTEL, CancellationPolicy.HOURS72, AccommodationApprovalStatus.APPROVED, ReservationMethod.AUTOMATIC,
                true,prices,null,null);
        when(accommodationService.findPriceList(1L)).thenReturn(listPrices);

        // Act
        List<IntervalPriceDTO> intervalPriceDTOS = priceListService.getIntervalPrices(1L);

        // Assert
        assertNotNull(intervalPriceDTOS);
        assertEquals(2, intervalPriceDTOS.size());

        assertEquals(100.0, intervalPriceDTOS.get(0).getPrice());
        assertEquals(LocalDate.now(), intervalPriceDTOS.get(0).getStartDate());
        assertEquals(LocalDate.now().plusDays(1), intervalPriceDTOS.get(0).getEndDate());

        assertEquals(200.0, intervalPriceDTOS.get(1).getPrice());
        assertEquals(LocalDate.now().plusDays(9), intervalPriceDTOS.get(1).getStartDate());
        assertEquals(LocalDate.now().plusDays(10), intervalPriceDTOS.get(1).getEndDate());

        verify(accommodationService).findPriceList(1L);
        verifyNoMoreInteractions(accommodationService);
    }

    @Test
    void test_getIntervalPrices_ordered_interval(){
        // Arrange
        Set<PriceList> prices =  new HashSet<>();
        prices.add(new PriceList(1L, LocalDate.now(), 100, AccommodationStatus.AVAILABLE));
        prices.add(new PriceList(2L, LocalDate.now().plusDays(1), 100, AccommodationStatus.AVAILABLE));
        prices.add(new PriceList(3L, LocalDate.now().plusDays(2), 200, AccommodationStatus.AVAILABLE));
        prices.add(new PriceList(4L, LocalDate.now().plusDays(3), 200, AccommodationStatus.AVAILABLE));
        List<PriceList> listPrices = new ArrayList<>(prices);
        listPrices.sort(Comparator.comparing(PriceList::getId));
        Accommodation accommodation = new Accommodation(1L,"Accommodation",null,null,null,1,10,
                AccommodationType.HOTEL, CancellationPolicy.HOURS72, AccommodationApprovalStatus.APPROVED, ReservationMethod.AUTOMATIC,
                true,prices,null,null);
        when(accommodationService.findPriceList(1L)).thenReturn(listPrices);

        // Act
        List<IntervalPriceDTO> intervalPriceDTOS = priceListService.getIntervalPrices(1L);

        // Assert
        assertNotNull(intervalPriceDTOS);
        assertEquals(2, intervalPriceDTOS.size());

        assertEquals(100.0, intervalPriceDTOS.get(0).getPrice());
        assertEquals(LocalDate.now(), intervalPriceDTOS.get(0).getStartDate());
        assertEquals(LocalDate.now().plusDays(1), intervalPriceDTOS.get(0).getEndDate());

        assertEquals(200.0, intervalPriceDTOS.get(1).getPrice());
        assertEquals(LocalDate.now().plusDays(2), intervalPriceDTOS.get(1).getStartDate());
        assertEquals(LocalDate.now().plusDays(3), intervalPriceDTOS.get(1).getEndDate());

        verify(accommodationService).findPriceList(1L);
        verifyNoMoreInteractions(accommodationService);
    }

    static Stream<Arguments> source_dates_prices_nothing_to_order_one_interval() {
        return Stream.of(
                arguments(LocalDate.of(2024, 1, 1), 1000.0),
                arguments(LocalDate.of(2024, 2, 1), 1000.0),
                arguments(LocalDate.of(2024, 1, 5), 1000.0)
        );
    }
    static Stream<Arguments> source_dates_prices_nothing_to_order_more_intervals() {
        return Stream.of(
                arguments(LocalDate.of(2024, 1, 1), 1000.0),
                arguments(LocalDate.of(2024, 2, 1), 1000.0),
                arguments(LocalDate.of(2024, 3, 1), 2000.0),
                arguments(LocalDate.of(2024, 4, 1), 2000.0),
                arguments(LocalDate.of(2024, 3, 5), 2000.0),
                arguments(LocalDate.of(2024, 1, 5), 1000.0)
        );
    }
    static Stream<Arguments> source_dates_prices_order_one_interval() {
        return Stream.of(
                arguments(LocalDate.of(2024, 1, 1), 1000.0),
                arguments(LocalDate.of(2024, 1, 5), 2000.0),
                arguments(LocalDate.of(2024, 1, 10), 2000.0),
                arguments(LocalDate.of(2024, 1, 4), 2000.0),    // did not set 3 because on front rendered day+1, so 4 is edge case
                arguments(LocalDate.of(2024, 1, 7), 2000.0)
        );
    }
    static Stream<Arguments> source_dates_prices_order_more_intervals() {
        return Stream.of(
                arguments(LocalDate.of(2024, 1, 1), 1000.0),
                arguments(LocalDate.of(2024, 1, 2), 1000.0),
                arguments(LocalDate.of(2024, 1, 4), 3000.0),    // did not set 3 because on front rendered day+1, so 4 is edge case
                arguments(LocalDate.of(2024, 1, 6), 3000.0),
                arguments(LocalDate.of(2024, 1, 8), 4000.0),    // did not set 7 because on front rendered day+1, so 8 is edge case
                arguments(LocalDate.of(2024, 1, 15), 4000.0)
        );
    }
}
