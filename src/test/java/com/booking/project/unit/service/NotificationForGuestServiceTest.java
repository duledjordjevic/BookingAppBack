package com.booking.project.unit.service;

import com.booking.project.dto.CreateNotificationForGuestDTO;
import com.booking.project.model.Guest;
import com.booking.project.model.NotificationForGuest;
import com.booking.project.model.User;
import com.booking.project.repository.inteface.IGuestRepository;
import com.booking.project.repository.inteface.INotificationForGuestRepository;
import com.booking.project.service.NotificationForGuestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class NotificationForGuestServiceTest {

    @Autowired
    private NotificationForGuestService notificationForGuestService;

    @MockBean
    private SimpMessagingTemplate simpMessagingTemplate;

    @MockBean
    private IGuestRepository guestRepository;

    @MockBean
    private INotificationForGuestRepository notificationForGuestRepository;

    @Test
    @DisplayName("Guest not found")
    public void test_create_when_guest_not_found() throws Exception {
        CreateNotificationForGuestDTO notificationForGuestDTO = new  CreateNotificationForGuestDTO("",1L);
        when(guestRepository.findById(1L)).thenReturn(Optional.empty());

        NotificationForGuest notificationForGuest = notificationForGuestService.create(notificationForGuestDTO);

        assertNull(notificationForGuest);
        verify(guestRepository).findById(1L);
        verifyNoMoreInteractions(guestRepository);
        verifyNoInteractions(simpMessagingTemplate);
        verifyNoInteractions(notificationForGuestRepository);
    }

    @Test
    @DisplayName("Test when notification is created")
    public void test_create_when_notification_created() throws Exception {
        User user = new User(); user.setId(1L);
        Guest guest = new Guest();
        guest.setId(1L);
        guest.setUser(user);
        CreateNotificationForGuestDTO notificationForGuestDTO = new  CreateNotificationForGuestDTO("",1L);
        when(guestRepository.findById(1L)).thenReturn(Optional.of(guest));
        doNothing().when(simpMessagingTemplate).convertAndSend(Optional.ofNullable(any()), any());

        NotificationForGuest notificationForGuest = notificationForGuestService.create(notificationForGuestDTO);

        assertNotNull(notificationForGuest);
        verify(guestRepository).findById(1L);
        verifyNoMoreInteractions(guestRepository);
        verify(notificationForGuestRepository).save(any());
        verifyNoMoreInteractions(notificationForGuestRepository);
    }
}
