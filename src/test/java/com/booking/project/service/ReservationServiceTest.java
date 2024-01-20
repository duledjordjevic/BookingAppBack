package com.booking.project.service;

import com.booking.project.dto.CreateNotificationForHostDTO;
import com.booking.project.dto.CreateReservationDTO;
import com.booking.project.model.*;
import com.booking.project.model.enums.*;
import com.booking.project.repository.inteface.IReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;
    @MockBean
    private IReservationRepository reservationRepository;
    @MockBean
    private AccommodationService accommodationService;
    @MockBean
    private GuestService guestService;
    @MockBean
    private NotificationForHostService notificationForHostService;
    @MockBean
    private NotificationTypeStatusService notificationTypeStatusService;

    @Test
    void test_create_reservation_when_accommodation_not_found() {
        when(accommodationService.findById(10L)).thenReturn(Optional.empty());
        CreateReservationDTO createReservationDTO = new CreateReservationDTO();
        createReservationDTO.setAccommodationId(10L);

        Reservation resault = null;

        try {
            resault = reservationService.create(createReservationDTO,10, ReservationMethod.AUTOMATIC);
        }catch (Exception e){
            fail("Method should not throw exception");
        }

        assertNull(resault);
        verify(accommodationService).findById(10L);
        verifyNoMoreInteractions(accommodationService);
        verifyNoInteractions(guestService);
        verifyNoInteractions(notificationTypeStatusService);
        verifyNoInteractions(notificationForHostService);
    }

    @Test
    void test_create_reservation_when_guest_not_found() throws Exception {
        Accommodation accommodation = new Accommodation(10L,"Accommodation",null,null,null,1,10,
                AccommodationType.HOTEL, CancellationPolicy.HOURS72, AccommodationApprovalStatus.APPROVED,ReservationMethod.AUTOMATIC,
                true,null,null,null);
        when(accommodationService.findById(10L)).thenReturn(Optional.of(accommodation));
        CreateReservationDTO createReservationDTO = new CreateReservationDTO();
        createReservationDTO.setGuestId(10L);
        createReservationDTO.setAccommodationId(10L);
        when(guestService.findByUser(10L)).thenReturn(Optional.empty());

        Reservation resault = reservationService.create(createReservationDTO,10,ReservationMethod.AUTOMATIC);

        assertNull(resault);
        verify(accommodationService).findById(10L);
        verify(guestService).findByUser(10L);
        verifyNoMoreInteractions(accommodationService);
        verifyNoMoreInteractions(guestService);
        verifyNoInteractions(notificationTypeStatusService);
        verifyNoInteractions(notificationForHostService);
    }
    @Test
    void test_create_reservation_when_res_method_automatic_and_not_enabled_notifications() throws Exception {
        Accommodation accommodation = new Accommodation(10L,"Accommodation",null,null,null,1,10,
                AccommodationType.HOTEL, CancellationPolicy.HOURS72, AccommodationApprovalStatus.APPROVED,ReservationMethod.AUTOMATIC,
                true,null,null,null);
        Host host = new Host();
        User user = new User();
        user.setId(10L);
        host.setUser(user);
        accommodation.setHost(host);
        CreateReservationDTO createReservationDTO = new CreateReservationDTO();
        createReservationDTO.setGuestId(10L);
        createReservationDTO.setAccommodationId(10L);
        Guest guest = new Guest();
        guest.setId(10L);
        when(accommodationService.findById(10L)).thenReturn(Optional.of(accommodation));
        when(guestService.findByUser(10L)).thenReturn(Optional.of(guest));
        when(notificationTypeStatusService.findStatusByUserAndType(10L, NotificationType.CREATED_RESERVATION)).
                thenReturn(false);
        when(reservationRepository.save(any())).thenReturn(null);

        Reservation resault = reservationService.create(createReservationDTO,10,ReservationMethod.AUTOMATIC);

        assertNotNull(resault);
        assertEquals(ReservationStatus.ACCEPTED,resault.getStatus());
        verify(reservationRepository).save(any());
        verify(accommodationService).findById(10L);
        verify(guestService).findByUser(10L);
        verify(notificationTypeStatusService).findStatusByUserAndType(10L,NotificationType.CREATED_RESERVATION);
        verifyNoMoreInteractions(notificationTypeStatusService);
        assertFalse(notificationTypeStatusService.findStatusByUserAndType(10L,NotificationType.CREATED_RESERVATION));
        verifyNoMoreInteractions(accommodationService);
        verifyNoMoreInteractions(guestService);
        verifyNoInteractions(notificationForHostService);
    }

    @Test
    void test_create_reservation_when_res_method_pending_and_not_enabled_notifications() throws Exception {
        Accommodation accommodation = new Accommodation(10L,"Accommodation",null,null,null,1,10,
                AccommodationType.HOTEL, CancellationPolicy.HOURS72, AccommodationApprovalStatus.APPROVED,ReservationMethod.MANUAL,
                true,null,null,null);
        Host host = new Host();
        User user = new User();
        user.setId(10L);
        host.setUser(user);
        accommodation.setHost(host);
        CreateReservationDTO createReservationDTO = new CreateReservationDTO();
        createReservationDTO.setGuestId( 10L);
        createReservationDTO.setAccommodationId(10L);
        Guest guest = new Guest();
        guest.setId(10L);
        when(accommodationService.findById(10L)).thenReturn(Optional.of(accommodation));
        when(guestService.findByUser(10L)).thenReturn(Optional.of(guest));
        when(notificationTypeStatusService.findStatusByUserAndType(10L, NotificationType.RESERVATION_REQUEST)).
                thenReturn(false);
        when(reservationRepository.save(any())).thenReturn(null);


        Reservation resault = reservationService.create(createReservationDTO,10,ReservationMethod.MANUAL);

        assertNotNull(resault);
        assertEquals(ReservationStatus.PENDING,resault.getStatus());
        verify(reservationRepository).save(any());
        verify(accommodationService).findById(10L);
        verify(guestService).findByUser(10L);
        verify(notificationTypeStatusService).findStatusByUserAndType(10L,NotificationType.RESERVATION_REQUEST);
        verifyNoMoreInteractions(notificationTypeStatusService);
        assertFalse(notificationTypeStatusService.findStatusByUserAndType(10L,NotificationType.RESERVATION_REQUEST));
        verifyNoMoreInteractions(accommodationService);
        verifyNoMoreInteractions(guestService);
        verifyNoInteractions(notificationForHostService);
    }
    @Test
    void test_create_reservations_when_notifications_enabled() throws Exception {
        Accommodation accommodation = new Accommodation(10L,"Accommodation",null,null,null,1,10,
                AccommodationType.HOTEL, CancellationPolicy.HOURS72, AccommodationApprovalStatus.APPROVED,ReservationMethod.MANUAL,
                true,null,null,null);
        Host host = new Host();
        host.setId(10L);
        User user = new User();
        user.setId(10L);
        host.setUser(user);
        accommodation.setHost(host);
        CreateReservationDTO createReservationDTO = new CreateReservationDTO();
        createReservationDTO.setGuestId( 10L);
        createReservationDTO.setAccommodationId(10L);
        Guest guest = new Guest();
        guest.setId(10L);
        guest.setName("Nikola");
        guest.setLastName("Maric");
        when(accommodationService.findById(10L)).thenReturn(Optional.of(accommodation));
        when(guestService.findByUser(10L)).thenReturn(Optional.of(guest));
        when(notificationTypeStatusService.findStatusByUserAndType(10L, NotificationType.RESERVATION_REQUEST)).
                thenReturn(true);
        when(notificationForHostService.create(any())).thenReturn(null);
        when(reservationRepository.save(any())).thenReturn(null);

        Reservation resault = reservationService.create(createReservationDTO,10,ReservationMethod.MANUAL);

        assertNotNull(resault);
        assertEquals(ReservationStatus.PENDING,resault.getStatus());
        verify(reservationRepository).save(any());
        verify(accommodationService).findById(10L);
        verify(guestService).findByUser(10L);
        verify(notificationTypeStatusService).findStatusByUserAndType(10L,NotificationType.RESERVATION_REQUEST);
        verify(notificationForHostService).create(any());
        verifyNoMoreInteractions(notificationTypeStatusService);
        verifyNoMoreInteractions(notificationForHostService);
        assertTrue(notificationTypeStatusService.findStatusByUserAndType(10L,NotificationType.RESERVATION_REQUEST));
        verifyNoMoreInteractions(accommodationService);
        verifyNoMoreInteractions(guestService);
    }

}
