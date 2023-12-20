package com.booking.project.controller;

import com.booking.project.dto.CreateNotificationForGuestDTO;
import com.booking.project.dto.NotificationForGuestDTO;
import com.booking.project.model.NotificationForGuest;
import com.booking.project.service.interfaces.INotificationForGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/notificationsForGuest")
public class NotificationForGuestController {

    @Autowired
    private INotificationForGuestService notificationForGuestService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationForGuest> createNotificationForGuest
            (@RequestBody CreateNotificationForGuestDTO createNotificationForGuestDTO) throws Exception {
        NotificationForGuest savedNotificationForGuest = notificationForGuestService.create(createNotificationForGuestDTO);
        return new ResponseEntity<NotificationForGuest>(savedNotificationForGuest, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('GUEST')")
    @GetMapping(value="/guest/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<NotificationForGuestDTO>> getNotificationsForGuest(@PathVariable Long id){
        Collection<NotificationForGuestDTO> notificationsForGuests = notificationForGuestService.findByGuest(id);
        return new ResponseEntity<Collection<NotificationForGuestDTO>>(notificationsForGuests, HttpStatus.OK);
    }

}
