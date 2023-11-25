package com.booking.project.service;

import com.booking.project.dto.CreateReservationDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.Guest;
import com.booking.project.model.Reservation;
import com.booking.project.model.enums.ReservationMethod;
import com.booking.project.model.enums.ReservationStatus;
import com.booking.project.repository.inteface.IAccommodationRepository;
import com.booking.project.repository.inteface.IGuestRepository;
import com.booking.project.repository.inteface.IReservationRepository;
import com.booking.project.service.interfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private IAccommodationRepository accommodationRepository;

    @Autowired
    private IGuestRepository guestRepository;
    @Override
    public Collection<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation save(Reservation reservation) throws Exception {
        return reservationRepository.save(reservation);
    }
    @Override
    public Reservation create(CreateReservationDTO createReservationDTO, double price, ReservationMethod reservationMethod) throws Exception {
        Reservation reservation = new Reservation();

        Optional<Accommodation> accommodation = accommodationRepository.findById(createReservationDTO.getAccommodationId());
        if (accommodation.isEmpty()) return null;
        reservation.setAccommodation(accommodation.get());

        Optional<Guest> guest = guestRepository.findById(createReservationDTO.getGuestId());
        if (guest.isEmpty()) return null;
        reservation.setGuest(guest.get());

        reservation.setPrice(price);
        if(reservationMethod == ReservationMethod.AUTOMATIC){
            reservation.setStatus(ReservationStatus.ACCEPTED);
        }else{
            reservation.setStatus(ReservationStatus.PENDING);
        }
        reservation.setStartDate(createReservationDTO.getStartDate());
        reservation.setEndDate(createReservationDTO.getEndDate());
        reservation.setNumberOfGuests(createReservationDTO.getNumberOfGuests());

        save(reservation);
        return  reservation;
    }
    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
