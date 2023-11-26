package com.booking.project.service;

import com.booking.project.dto.AccommodationDTO;
import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.repository.inteface.IAccommodationRepository;
import com.booking.project.service.interfaces.IAccommodationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

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
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Accommodation changeAvailableStatus(Long id, Boolean isAvailable) throws Exception {
        Optional<Accommodation> accommodation = findById(id);

        if(accommodation.isEmpty()) return null;

        accommodation.get().setAvailableForReservation(isAvailable);
        save(accommodation.get());

        return accommodation.get();
    }
    public Collection<Accommodation> findAccomodationsByHostId(Long id){
        System.out.println("Uso");
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

}


