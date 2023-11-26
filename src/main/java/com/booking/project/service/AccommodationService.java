package com.booking.project.service;

import com.booking.project.dto.AccommodationDTO;
import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.PriceList;
import com.booking.project.model.enums.AccommodationStatus;
import com.booking.project.model.enums.ReservationMethod;
import com.booking.project.repository.inteface.IAccommodationRepository;
import com.booking.project.service.interfaces.IAccommodationService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.*;

@Service
public class AccommodationService implements IAccommodationService {
    @Autowired
    private IAccommodationRepository accommodationRepository;
    @Autowired
    private EntityManager em;
    @Override
    public Collection<AccommodationDTO> findAll() {

        Collection<Accommodation> accommodations = accommodationRepository.findAll();
        Collection<AccommodationDTO> accommodationDTOS = new ArrayList<>();
        for(Accommodation acc: accommodations){
            AccommodationDTO accomodationDTO = new AccommodationDTO(acc);
            accommodationDTOS.add(accomodationDTO);
        }
        return accommodationDTOS;
    }
    public Collection<AccommodationCardDTO> findAllCards(){
        Collection<Accommodation> accommodations = accommodationRepository.findAll();
        Collection<AccommodationCardDTO> accommodationDTOS = new ArrayList<>();
        for(Accommodation acc : accommodations){
            AccommodationCardDTO accomodationDTO = new AccommodationCardDTO(acc);
            accommodationDTOS.add(accomodationDTO);
        }
        return accommodationDTOS;
    }
    public Optional<AccommodationDTO> changeAccommodations(AccommodationDTO accommodationDTO,Long id) throws Exception {
        Optional<Accommodation> accommodation = findById(id);

        if(accommodation.isEmpty()) return null;

        accommodation.get().setTitle(accommodationDTO.getTitle());
        accommodation.get().setDescription(accommodationDTO.getDescription());
        accommodation.get().setAddress(accommodationDTO.getAddress());
        accommodation.get().setAmenities(accommodationDTO.getAmenities());
        accommodation.get().setPhotos(accommodationDTO.getPhotos());
        accommodation.get().setMinGuests(accommodationDTO.getMinGuest());
        accommodation.get().setMaxGuests(accommodationDTO.getMaxGuest());
        accommodation.get().setType(accommodationDTO.getType());
        accommodation.get().setCancellationPolicy(accommodationDTO.getCancellationPolicy());
        accommodation.get().setReservationMethod(accommodationDTO.getReservationMethod());
        accommodation.get().setAvailableForReservation(accommodationDTO.isAvailableForReservation());
        accommodation.get().setPriceForEntireAcc(accommodationDTO.isPriceForEntireAcc());
        accommodation.get().setPrices(accommodationDTO.getPrices());

        save(accommodation.get());

        return Optional.of(accommodationDTO);
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
                    if (priceList.getStatus() == AccommodationStatus.AVAILABLE && accommodation.get().getReservationMethod() == ReservationMethod.AUTOMATIC){
                        if(accommodation.get().getReservationMethod().equals(ReservationMethod.AUTOMATIC)){
                            priceLists.add(priceList);
                        }
                    }else{
                        return new ArrayList<Object>(List.of(false));
                    }
                }
            }
            for(PriceList priceList : priceLists){
                priceList.setStatus(AccommodationStatus.RESERVED);
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
    public Boolean changePriceList(LocalDate startDate, LocalDate endDate, Long id, AccommodationStatus accommodationStatus) throws Exception {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);

        if(accommodation.isEmpty()) return false;

        changeAccommodationStatus(accommodation.get(), startDate, endDate, accommodationStatus);
        save(accommodation.get());

        return true;
    }

    private void changeAccommodationStatus(Accommodation accommodation, LocalDate startDate, LocalDate endDate, AccommodationStatus accommodationStatus){
        for(PriceList priceList : accommodation.getPrices()){
            if ((priceList.getDate().isAfter(startDate) || priceList.getDate().isEqual(startDate) ) && (priceList.getDate().isBefore(endDate) || priceList.getDate().isEqual(endDate))){
                priceList.setStatus(accommodationStatus);
            }
        }
    }


    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public AccommodationDTO changeAvailableStatus(Long id, Boolean isAvailable) throws Exception {
        Optional<Accommodation> accommodation = findById(id);

        if(accommodation.isEmpty()) return null;

        accommodation.get().setAvailableForReservation(isAvailable);
        save(accommodation.get());

        AccommodationDTO accommodationDTO = new AccommodationDTO(accommodation.get());

        return accommodationDTO;
    }
    public Collection<Accommodation> findAccomodationsByHostId(Long id){
        return accommodationRepository.findAccommodationsByHostId(id);
    }
    public Collection<AccommodationDTO> filterAccommodations(LocalDate startDate,LocalDate endDate,Integer numOfGuests,String city){
        Collection<Accommodation> accommodations = accommodationRepository.filterAccommodations(startDate,endDate,city,numOfGuests);

        Collection<AccommodationDTO> accommodationDTOS = new ArrayList<>();
        for(Accommodation acc: accommodations){
            AccommodationDTO accomodationDTO = new AccommodationDTO(acc);
            accommodationDTOS.add(accomodationDTO);
        }

        return accommodationDTOS;
    }
    public AccommodationDTO findAccommodationsDetails(Long id){
        Optional<Accommodation> accommodation = findById(id);

        if(accommodation.isEmpty()) return null;

        AccommodationDTO accommodationDTO = new AccommodationDTO(accommodation.get());
        return accommodationDTO;
    }

    @Override
    public AccommodationDTO changeAccommodationReservationMethod(Long id, ReservationMethod reservationMethod) throws Exception {
        Optional<Accommodation> accommodation = findById(id);

        if(accommodation.isEmpty()) return null;

        accommodation.get().setReservationMethod(reservationMethod);
        save(accommodation.get());

        AccommodationDTO accommodationDTO = new AccommodationDTO(accommodation.get());

        return accommodationDTO;
    }



}


