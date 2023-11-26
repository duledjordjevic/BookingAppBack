package com.booking.project.controller;

import com.booking.project.model.NotificationForGuest;
import com.booking.project.model.NotificationForHost;
import com.booking.project.service.NotificationForHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/notificationsForHost")
public class NotificationForHostController {

    @Autowired
    private NotificationForHostService notificationForHostService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationForHost> createNotificationForHost
            (@RequestBody NotificationForHost notificationForHost) throws Exception {
        NotificationForHost savedNotificationForHost = notificationForHostService.save(notificationForHost);
        return new ResponseEntity<NotificationForHost>(savedNotificationForHost, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<NotificationForHost>> getNotificationsForHost(@RequestBody Long id){
        Collection<NotificationForHost> notificationForHost = notificationForHostService.findByHost(id);
        return new ResponseEntity<Collection<NotificationForHost>>(notificationForHost, HttpStatus.OK);
    }
}