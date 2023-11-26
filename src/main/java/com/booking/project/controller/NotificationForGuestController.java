package com.booking.project.controller;

import com.booking.project.model.NotificationForGuest;
import com.booking.project.service.interfaces.INotificationForGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/notificationsForGuest")
public class NotificationForGuestController {

    @Autowired
    private INotificationForGuestService notificationForGuestService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationForGuest> createNotificationForGuest
            (@RequestBody NotificationForGuest notificationForGuest) throws Exception {
        NotificationForGuest savedNotificationForGuest = notificationForGuestService.save(notificationForGuest);
        return new ResponseEntity<NotificationForGuest>(savedNotificationForGuest, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<NotificationForGuest>> getNotificationsForGuest(@RequestBody Long id){
        Collection<NotificationForGuest> notificationsForGuests = notificationForGuestService.findByGuest(id);
        return new ResponseEntity<Collection<NotificationForGuest>>(notificationsForGuests, HttpStatus.OK);
    }

}
