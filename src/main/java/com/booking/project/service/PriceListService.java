package com.booking.project.service;

import com.booking.project.dto.IntervalPriceDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.PriceList;
import com.booking.project.model.enums.AccommodationStatus;
import com.booking.project.service.interfaces.IPriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PriceListService implements IPriceListService {
    @Autowired
    private AccommodationService accommodationService;

    private Map<LocalDate, Double> orderIntervals(List<IntervalPriceDTO> priceIntervals) {

        Map<LocalDate, Double> intervals = new HashMap<>();

        LocalDate date;
        LocalDate start;
        LocalDate end;

        for (int i = priceIntervals.size()-1; i >= 0; i--) {

            start = priceIntervals.get(i).getStartDate().plusDays(1);
            end = priceIntervals.get(i).getEndDate().plusDays(1);
            Double price = priceIntervals.get(i).getPrice();

            while(!start.isAfter(end)) {
                date = start;
                if (!intervals.containsKey(date)) {
                    intervals.put(date, price);
                }
                start = start.plusDays(1);
            }
        }

        return intervals;
    }

    private Set<PriceList> convertToPriceList(Map<LocalDate, Double> intervals) {
        Set<PriceList> priceList = new HashSet<>();

        for(LocalDate date: intervals.keySet()) {
            priceList.add(new PriceList(null, date, intervals.get(date), AccommodationStatus.AVAILABLE));
        }

        return priceList;
    }

    @Override
    public Set<PriceList> getPriceList(List<IntervalPriceDTO> priceIntervals) {
        return convertToPriceList(orderIntervals(priceIntervals));
    }

    @Override
    public int updatePriceList(Long id, List<IntervalPriceDTO> priceIntervals) throws Exception {
        Optional<Accommodation> accommodation = accommodationService.findById(id);
        if(accommodation.isEmpty()) return 0;
        Map<LocalDate, Double> intervals = orderIntervals(priceIntervals);

        LocalDate date;
        Set<PriceList> priceLists = accommodation.get().getPrices();

        for (PriceList priceList : priceLists) {

            date = priceList.getDate();

            if (priceList.getStatus() == AccommodationStatus.AVAILABLE && intervals.containsKey(date)) {
                priceList.setPrice(intervals.get(date));
                intervals.remove(date);
            }

        }

        priceLists.addAll(convertToPriceList(intervals));
        accommodation.get().setPrices(priceLists);
        accommodationService.save(accommodation.get());

        return priceLists.size();
    }

    @Override
    public List<IntervalPriceDTO> getIntervalPrices(Long accommodationId) {

        List<IntervalPriceDTO> priceIntervals = new ArrayList<>();
        List<PriceList> priceLists = accommodationService.findPriceList(accommodationId);

        PriceList current = null;
        PriceList last =  priceLists.get(0);

        LocalDate start = last.getDate();

        for(int i = 1; i < priceLists.size(); i++) {

            current = priceLists.get(i);

            if (!current.getDate().isEqual(last.getDate().plusDays(1)) || current.getPrice() != last.getPrice())  {
                priceIntervals.add(new IntervalPriceDTO(start, last.getDate(), last.getPrice()));
                start = current.getDate();
            }

            last = current;
        }

        priceIntervals.add(new IntervalPriceDTO(
                start,
                current != null ? current.getDate() : last.getDate(),
                current.getPrice()));

//        current != null ? current.getPrice() : 0));
        return priceIntervals;
    }


}
