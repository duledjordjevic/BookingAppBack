package com.booking.project.service;

import com.booking.project.dto.AccommodationCardDTO;
import com.booking.project.dto.GuestDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.Guest;
import com.booking.project.repository.inteface.IGuestRepository;
import com.booking.project.service.interfaces.IAccommodationService;
import com.booking.project.service.interfaces.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class GuestService implements IGuestService {
    @Autowired
    private IGuestRepository repository;
    @Autowired
    private IAccommodationService accommodationService;
    @Autowired
    private ImageService imageService;
    @Override
    public Collection<Guest> findAll() {
        return repository.findAll();
    }


    @Override
    public Optional<Guest> findById(Long id) {
        return repository.findById(id);
    }
    @Override
    public Optional<Guest> findByUser(Long id){
        return repository.findByUserId(id);
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
    public Collection<AccommodationCardDTO> findFavourites(Long guestUserid) throws IOException {
        Collection<Accommodation> favourites = repository.findByFavourites(guestUserid);
        Collection<AccommodationCardDTO> accommodationDTOS = new ArrayList<>();
        for(Accommodation acc: favourites){
            AccommodationCardDTO accommodationDTO = new AccommodationCardDTO(acc);
            accommodationDTO.setImage(imageService.getCoverImage(acc.getImages().split(",")[0]));
            accommodationDTOS.add(accommodationDTO);
        }

        return accommodationDTOS;
    }

    @Override
    public boolean addFavourite(Long accommodationId, Long guestUserId) throws Exception {
        Optional<Guest> guest = findByUser(guestUserId);

        if(guest.isEmpty()) return false;

        Optional<Accommodation> accommodation = accommodationService.findById(accommodationId);

        if(accommodation.isEmpty()) return false;


        guest.get().getFavourites().add(accommodation.get());

        save(guest.get());

        return true;
    }

    @Override
    public boolean isFavourite(Long accommodationId, Long guestUserId) throws Exception{
        Optional<Guest> guest = findByUser(guestUserId);

        if(guest.isEmpty()) return false;

        Optional<Accommodation> accommodation = accommodationService.findById(accommodationId);

        return accommodation.filter(value -> guest.get().getFavourites().contains(value)).isPresent();

    }

    @Override
    public boolean removeFavourite(Long accommodationId, Long guestUserId) throws Exception{
        Optional<Guest> guest = findByUser(guestUserId);

        if(guest.isEmpty()) return false;

        Optional<Accommodation> accommodation = accommodationService.findById(accommodationId);

        if(accommodation.isEmpty()) return false;

        guest.get().getFavourites().remove(accommodation.get());

        save(guest.get());
        return true;
    }
    @Override
    public Collection<AccommodationCardDTO> findAllAccommodationsWithFavourites(Long id) throws IOException {
        Collection<AccommodationCardDTO> allAccommodations = accommodationService.findAllCards();
        Collection<AccommodationCardDTO> guestFavourites = findFavourites(id);
        for(AccommodationCardDTO accommodation: allAccommodations){
            if(guestFavourites.stream().anyMatch(card -> card.getId().equals(accommodation.getId()))){
                accommodation.setIsFavourite(true);
            }else{
                accommodation.setIsFavourite(false);
            }
        }
        return allAccommodations;
    }
}
