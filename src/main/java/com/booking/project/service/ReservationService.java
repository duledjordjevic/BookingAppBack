package com.booking.project.service;

import com.booking.project.dto.CreateNotificationForGuestDTO;
import com.booking.project.dto.CreateNotificationForHostDTO;
import com.booking.project.dto.CreateReservationDTO;
import com.booking.project.dto.ReservationDTO;
import com.booking.project.model.Accommodation;
import com.booking.project.model.Guest;
import com.booking.project.model.NotificationTypeStatus;
import com.booking.project.model.Reservation;
import com.booking.project.model.enums.*;
import com.booking.project.repository.inteface.IReservationRepository;
import com.booking.project.service.interfaces.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private IAccommodationService accommodationService;

    @Autowired
    private IGuestService guestService;

    @Autowired
    EntityManager em;
    @Autowired
    private INotificationForHostService notificationForHostService;
    @Autowired
    private INotificationForGuestService notificationForGuestService;
    @Autowired
    private INotificationTypeStatusService notificationTypeStatusService;
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

        Optional<Accommodation> accommodation = accommodationService.findById(createReservationDTO.getAccommodationId());
        if (accommodation.isEmpty()) return null;
        reservation.setAccommodation(accommodation.get());

        Optional<Guest> guest = guestService.findByUser(createReservationDTO.getGuestId());
        if (guest.isEmpty()) return null;
        reservation.setGuest(guest.get());

        CreateNotificationForHostDTO notificationForHostDTO = new CreateNotificationForHostDTO();
        notificationForHostDTO.setHostId(accommodation.get().getHost().getId());
        Boolean notificationStatus;

        reservation.setPrice(price);
        if(reservationMethod == ReservationMethod.AUTOMATIC){
            reservation.setStatus(ReservationStatus.ACCEPTED);

            notificationForHostDTO.setType(NotificationType.CREATED_RESERVATION);
            notificationForHostDTO.setDescription(guest.get().getName() + " " + guest.get().getLastName() +
                    " create a reservation for accommodation " + accommodation.get().getTitle());
            notificationStatus = notificationTypeStatusService.findStatusByUserAndType(accommodation.get().getHost().getUser().getId(),
                    NotificationType.CREATED_RESERVATION);
        }else{
            reservation.setStatus(ReservationStatus.PENDING);

            notificationForHostDTO.setType(NotificationType.RESERVATION_REQUEST);
            notificationForHostDTO.setDescription(guest.get().getName() + " " + guest.get().getLastName() +
                    " sent a reservation request for accommodation " + accommodation.get().getTitle());
            notificationStatus = notificationTypeStatusService.findStatusByUserAndType(accommodation.get().getHost().getUser().getId(),
                    NotificationType.RESERVATION_REQUEST);
        }
        if(notificationStatus){
            notificationForHostService.create(notificationForHostDTO);
        }


        reservation.setStartDate(createReservationDTO.getStartDate());
        reservation.setEndDate(createReservationDTO.getEndDate());
        reservation.setNumberOfGuests(createReservationDTO.getNumberOfGuests());
        reservation.setHostReported(false);

        save(reservation);
        return  reservation;
    }
    @Override
    public Boolean deleteById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);

        if(reservation.isEmpty()){
            return false;
        }
        if (reservation.get().getStatus().equals(ReservationStatus.PENDING)){
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public Collection<Reservation> findByGuestId(Long id){
        return reservationRepository.findByGuest(id);
    }
    @Override
    public Collection<Reservation> findByHostId(Long id){
        return reservationRepository.findByHost(id);
    }
    @Override
    public List<ReservationDTO> filterGuestReservations(String title, LocalDate startDate, LocalDate endDate, ReservationStatus reservationStatus, Long guestUserId){
        Query q = em.createQuery("SELECT r " +
                "FROM Reservation r " +
                "JOIN FETCH r.accommodation a " +
                "JOIN FETCH r.guest " +
                "WHERE (LOWER(a.title) LIKE LOWER(:pattern) OR :pattern is Null) " +
                "AND ((r.startDate >= :startDate AND r.endDate <= :endDate) OR cast(:startDate as date) is null) " +
                "AND (r.status = :reservationStatus OR :reservationStatus is Null) AND (r.guest.user.id = :guestUserId)");
        if(title == null){
            q.setParameter("pattern", null);
        }else{
            q.setParameter("pattern", "%" + title + "%");
        }
        q.setParameter("startDate" , startDate);
        q.setParameter("endDate", endDate);
        q.setParameter("reservationStatus", reservationStatus);
        q.setParameter("guestUserId", guestUserId);

        List<ReservationDTO> reservationDTOs = new ArrayList<ReservationDTO>();
        List<Reservation> reservations = q.getResultList();
        for(Reservation reservation : reservations){
            reservationDTOs.add(new ReservationDTO(reservation));
        }
        return reservationDTOs;
    }

    @Override
    public List<ReservationDTO> filterHostReservations(String title, LocalDate startDate, LocalDate endDate, ReservationStatus reservationStatus, Long hostUserId){
        Query q = em.createQuery("SELECT r FROM Reservation r " +
                "JOIN FETCH r.accommodation a " +
                "JOIN FETCH r.guest " +
                "WHERE (LOWER(a.title) LIKE LOWER(:pattern) OR :pattern is Null) " +
                "AND ((r.startDate >= :startDate AND r.endDate <= :endDate) OR cast(:startDate as date) is null) " +
                "AND (r.status = :reservationStatus OR :reservationStatus is Null) " +
                "AND (a.host.user.id = :hostUserId)");
        if(title == null){
            q.setParameter("pattern", null);
        }else{
            q.setParameter("pattern", "%" + title + "%");
        }
        q.setParameter("startDate" , startDate);
        q.setParameter("endDate", endDate);
        q.setParameter("reservationStatus", reservationStatus);
        q.setParameter("hostUserId", hostUserId);

        List<ReservationDTO> reservationDTOs = new ArrayList<ReservationDTO>();
        List<Reservation> reservations = q.getResultList();
        for(Reservation reservation : reservations){
            reservationDTOs.add(new ReservationDTO(reservation));
        }
        return reservationDTOs;
    }

    @Override
    public Reservation updateStatus(Long id, ReservationStatus reservationStatus) throws Exception {
        Optional<Reservation> reservation = reservationRepository.findById(id);

        if(reservation.isEmpty()) return null;
        Boolean notificationTurnedStatus = notificationTypeStatusService.findStatusByUserAndType(reservation.get().getGuest().getUser().getId(),
                NotificationType.RESERVATION_REQUEST_RESPOND);
        String notificationDescription = "";

        if(reservationStatus.equals(ReservationStatus.ACCEPTED)){
            List<Reservation> overlapsReservations = reservationRepository.getOverlaps(reservation.get().getStartDate(), reservation.get().getEndDate(), reservation.get().getAccommodation().getId(), ReservationStatus.PENDING);
            for(Reservation reservationToDecline : overlapsReservations){
                reservationToDecline.setStatus(ReservationStatus.DECLINED);
                reservationRepository.save(reservationToDecline);
            }
            notificationDescription = reservation.get().getAccommodation().getHost().getName() + " " + reservation.get().getAccommodation().getHost().getLastName() +
                    " accepted your reservation request for accommodation: " + reservation.get().getAccommodation().getTitle();

            accommodationService.changePriceList(reservation.get().getStartDate(),reservation.get().getEndDate(), reservation.get().getAccommodation().getId(), AccommodationStatus.RESERVED);
        }else if(reservationStatus.equals(ReservationStatus.DECLINED)){
            notificationDescription = reservation.get().getAccommodation().getHost().getName() + " " + reservation.get().getAccommodation().getHost().getLastName() +
                    " declined your reservation request for accommodation: " + reservation.get().getAccommodation().getTitle();
        }


        if(notificationTurnedStatus){
            CreateNotificationForGuestDTO notificationForGuestDTO = new  CreateNotificationForGuestDTO(notificationDescription,
                    reservation.get().getGuest().getId());
            notificationForGuestService.create(notificationForGuestDTO);
        }

        reservation.get().setStatus(reservationStatus);
        reservationRepository.save(reservation.get());

        return reservation.get();
    }

    @Override
    public Reservation cancelAcceptedReservation(Long id) throws Exception {
        Optional<Reservation> reservation = reservationRepository.findById(id);

        if(reservation.isEmpty()) return null;

        String notificationDescription = reservation.get().getGuest().getName() + " " + reservation.get().getGuest().getLastName() +
                " cancelled a reservation for accommodation " + reservation.get().getAccommodation().getTitle();
        Boolean notificationTurnedStatus = notificationTypeStatusService.findStatusByUserAndType(reservation.get().getAccommodation().getHost().getUser().getId(),
                NotificationType.CANCELLED_RESERVATION);

        if(reservation.get().getAccommodation().getCancellationPolicy().equals(CancellationPolicy.HOURS24)){
            if (!reservation.get().getStartDate().minusDays(1).isEqual(LocalDate.now())){
                reservation.get().setStatus(ReservationStatus.CANCELLED);
                if(notificationTurnedStatus){
                    CreateNotificationForHostDTO notificationForHostDTO = new CreateNotificationForHostDTO(NotificationType.CANCELLED_RESERVATION,
                            notificationDescription,reservation.get().getAccommodation().getHost().getId());
                    notificationForHostService.create(notificationForHostDTO);
                }
                return reservation.get();
            }
        }else if(reservation.get().getAccommodation().getCancellationPolicy().equals(CancellationPolicy.HOURS48)){
            if (!reservation.get().getStartDate().minusDays(2).isEqual(LocalDate.now())){
                reservation.get().setStatus(ReservationStatus.CANCELLED);
                if(notificationTurnedStatus){
                    CreateNotificationForHostDTO notificationForHostDTO = new CreateNotificationForHostDTO(NotificationType.CANCELLED_RESERVATION,
                            notificationDescription,reservation.get().getAccommodation().getHost().getId());
                    notificationForHostService.create(notificationForHostDTO);
                }
                return reservation.get();
            }
        }else if(reservation.get().getAccommodation().getCancellationPolicy().equals(CancellationPolicy.HOURS72)){
            if (!reservation.get().getStartDate().minusDays(3).isEqual(LocalDate.now())){
                reservation.get().setStatus(ReservationStatus.CANCELLED);
                if(notificationTurnedStatus){
                    CreateNotificationForHostDTO notificationForHostDTO = new CreateNotificationForHostDTO(NotificationType.CANCELLED_RESERVATION,
                            notificationDescription,reservation.get().getAccommodation().getHost().getId());
                    notificationForHostService.create(notificationForHostDTO);
                }
                return reservation.get();
            }
        }
        return null;
    }

//    private List<Reservation> getOverlaps(LocalDate startDate, LocalDate endDate, ReservationStatus reservationStatus){
//        Query q = em.createQuery("SELECT r FROM Reservation  r WHERE (:startDate <= r.endDate AND :endDate >= r.startDate) AND r.status = :reservationStatus");
//        q.setParameter("startDate", startDate);
//        q.setParameter("endDate", endDate);
//        q.setParameter("reservationStatus", reservationStatus);
//        return q.getResultList();
//    }
    @Override
    public void cancellGuestReservations(Long id){
        reservationRepository.cancellGuestReservation(id,ReservationStatus.CANCELLED);
    }

}
