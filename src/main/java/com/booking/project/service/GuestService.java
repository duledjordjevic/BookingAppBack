package com.booking.project.service;

import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.dto.GuestDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.Guest;
import com.booking.project.repository.inteface.IGuestRepository;
import com.booking.project.service.interfaces.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class GuestService implements IGuestService {
    @Autowired
    private IGuestRepository repository;
    @Override
    public Collection<Guest> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Guest> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Guest save(Guest guest) throws Exception {
        return repository.save(guest);
    }

    @Override
    public Guest update(GuestDTO guestDTO, Long id) throws Exception{
        Optional<Guest> guestForUpdate = findById(id);

        if(guestForUpdate.isEmpty()) return null;

        guestForUpdate.get().setName(guestDTO.getName());
        guestForUpdate.get().setLastName(guestDTO.getLastName());
        guestForUpdate.get().setAddress(guestDTO.getAddress());
        guestForUpdate.get().setPhoneNumber(guestDTO.getPhoneNumber());
        guestForUpdate.get().setNotificationEnabled(guestDTO.isNotificationEnabled());
        guestForUpdate.get().getUser().copyValues(guestDTO.getUserCredentialsDTO());

        save(guestForUpdate.get());
        return guestForUpdate.get();
    }

    @Override
    public Guest addNumberOfCancellation(Long id){
        Optional<Guest> guestForUpdate = findById(id);

        if(guestForUpdate.isEmpty()) return null;

        guestForUpdate.get().setNumberOfCancellation(guestForUpdate.get().getNumberOfCancellation() + 1);
        repository.save(guestForUpdate.get());

        return guestForUpdate.get();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public GuestDTO addGuest(GuestDTO guestDTO) throws Exception {
        Guest guest = new Guest(guestDTO);
        Guest savedGuest = save(guest);

        if(savedGuest == null) return null;

        return guestDTO;
    }
    public Collection<AccommodationCardDTO> findFavourites(Long id){
        Collection<Accommodation> favourites = repository.findByFavourites(id);
        Collection<AccommodationCardDTO> accommodationDTOS = new ArrayList<>();
        for(Accommodation acc: favourites){
            AccommodationCardDTO accomodationDTO = new AccommodationCardDTO(acc);
            accommodationDTOS.add(accomodationDTO);
        }

        return accommodationDTOS;

    }
}
