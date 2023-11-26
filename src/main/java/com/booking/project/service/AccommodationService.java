package com.booking.project.service;

import com.booking.project.model.Accommodation;
import com.booking.project.model.PriceList;
import com.booking.project.model.enums.AccomodationStatus;
import com.booking.project.model.enums.ReservationMethod;
import com.booking.project.repository.inteface.IAccommodationRepository;
import com.booking.project.service.interfaces.IAccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AccommodationService implements IAccommodationService {
    @Autowired
    private IAccommodationRepository accommodationRepository;
    @Override
    public Collection<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public  Accommodation save(Accommodation accommodation) throws Exception {
        return accommodationRepository.save(accommodation);
    }
    @Override
    public List<Object> reservate(Long accommodationId, LocalDate startDate, LocalDate endDate, int numberOfGuests) throws Exception {
        Optional<Accommodation> accommodation = accommodationRepository.findById(accommodationId);

        if(accommodation.isEmpty())  return new ArrayList<Object>(List.of(false));

        double price = 0;
        if(accommodation.get().isAvailableForReservation() && (accommodation.get().getMinGuests() <= numberOfGuests && accommodation.get().getMaxGuests() >= numberOfGuests) ){
            List<PriceList> priceLists = new ArrayList<PriceList>();
            for(PriceList priceList : accommodation.get().getPrices()){
                if((priceList.getDate().isAfter(startDate) || priceList.getDate().isEqual(startDate))  && (priceList.getDate().isBefore(endDate) || priceList.getDate().isEqual(endDate))){
                    if (priceList.getStatus() == AccomodationStatus.AVAILABLE && accommodation.get().getReservationMethod() == ReservationMethod.AUTOMATIC){
                        if(accommodation.get().getReservationMethod().equals(ReservationMethod.AUTOMATIC)){
                            priceLists.add(priceList);
                        }
                    }else{
                        return new ArrayList<Object>(List.of(false));
                    }
                }
            }
            for(PriceList priceList : priceLists){
                priceList.setStatus(AccomodationStatus.RESERVED);
                    price += priceList.getPrice();
            }
            if(!accommodation.get().isPriceForEntireAcc()){
                price = price * numberOfGuests;
            }
        }else{
            return new ArrayList<Object>(List.of(false));
        }
        save(accommodation.get());
        return new ArrayList<Object>(List.of(false, price, accommodation.get().getReservationMethod()));
    }

    @Override
    public Boolean reservateDates(LocalDate startDate, LocalDate endDate, Long id) throws Exception {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);

        if(accommodation.isEmpty()) return false;

        for(PriceList priceList : accommodation.get().getPrices()){
            if ((priceList.getDate().isAfter(startDate) || priceList.getDate().isEqual(startDate) ) && (priceList.getDate().isBefore(endDate) || priceList.getDate().isEqual(endDate))){
                priceList.setStatus(AccomodationStatus.RESERVED);
            }
        }
        save(accommodation.get());

        return true;
    }
    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }
}


