package com.booking.project.controller;

import com.booking.project.dto.CreateNotificationForHostDTO;
import com.booking.project.dto.NotificationForHostDTO;
import com.booking.project.model.NotificationForHost;
import com.booking.project.service.NotificationForHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/notificationsForHost")
public class NotificationForHostController {

    @Autowired
    private NotificationForHostService notificationForHostService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationForHost> createNotificationForHost
            (@RequestBody CreateNotificationForHostDTO createNotificationForHostDTO) throws Exception {
        NotificationForHost savedNotificationForHost = notificationForHostService.create(createNotificationForHostDTO);
        return new ResponseEntity<NotificationForHost>(savedNotificationForHost, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('HOST')")
    @GetMapping(value = "/host/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<NotificationForHostDTO>> getNotificationsForHost(@PathVariable Long id){
        Collection<NotificationForHostDTO> notificationForHost = notificationForHostService.findByHost(id);
        return new ResponseEntity<Collection<NotificationForHostDTO>>(notificationForHost, HttpStatus.OK);
    }
}
