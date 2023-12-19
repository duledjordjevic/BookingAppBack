package com.booking.project.service.interfaces;

import com.booking.project.dto.IntervalPriceDTO;
import com.booking.project.model.PriceList;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IPriceListService {

    Set<PriceList> getPriceList(List<IntervalPriceDTO> priceIntervals);
    int updatePriceList(Long id, List<IntervalPriceDTO> priceIntervals) throws Exception;
    List<IntervalPriceDTO> getIntervalPrices(Long accommodationId);
}
