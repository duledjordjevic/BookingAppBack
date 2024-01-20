package com.booking.project.unit.service;

import com.booking.project.dto.CreateNotificationForHostDTO;
import com.booking.project.model.Host;
import com.booking.project.model.NotificationForHost;
import com.booking.project.model.User;
import com.booking.project.model.enums.NotificationType;
import com.booking.project.repository.inteface.IHostRepository;
import com.booking.project.repository.inteface.INotificationForHostRepository;
import com.booking.project.service.NotificationForHostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class NotificationForHostServiceTest {
    @Autowired
    private NotificationForHostService notificationForHostService;

    @MockBean
    private IHostRepository hostRepository;

    @MockBean
    private INotificationForHostRepository notificationForHostRepository;

    @MockBean
    private SimpMessagingTemplate simpMessagingTemplate;

    @Test
    void test_create_when_host_not_found() throws Exception {

        CreateNotificationForHostDTO createNotificationForHostDTO = new CreateNotificationForHostDTO(NotificationType.CREATED_RESERVATION,
                " asdfadsf",10L);
        when(hostRepository.findById(10L)).thenReturn(Optional.empty());

        NotificationForHost result = notificationForHostService.create(createNotificationForHostDTO);

        assertNull(result);
        verify(hostRepository).findById(10L);
        verifyNoMoreInteractions(hostRepository);
        verifyNoInteractions(notificationForHostRepository);
        verifyNoInteractions(simpMessagingTemplate);
    }

    @Test
    void test_create_succesfully() throws Exception {
        CreateNotificationForHostDTO createNotificationForHostDTO = new CreateNotificationForHostDTO(NotificationType.CREATED_RESERVATION,
                " asdfadsf",10L);
        Host host = new Host();
        User user = new User();
        user.setId(10L);
        host.setUser(user);
        when(hostRepository.findById(10L)).thenReturn(Optional.of(host));
        doNothing().when(simpMessagingTemplate).convertAndSend(Optional.ofNullable(any()), any());

        NotificationForHost result = notificationForHostService.create(createNotificationForHostDTO);

        assertNotNull(result);
        verify(hostRepository).findById(10L);
        verifyNoMoreInteractions(hostRepository);
        verify(notificationForHostRepository).save(any());
        verifyNoMoreInteractions(notificationForHostRepository);
    }
}