package com.booking.project.controller;

import com.booking.project.dto.CreateNotificationForHostDTO;
import com.booking.project.dto.NotificationForHostDTO;
import com.booking.project.dto.NotificationTypeStatusDTO;
import com.booking.project.model.NotificationForHost;
import com.booking.project.model.NotificationTypeStatus;
import com.booking.project.service.NotificationForHostService;
import com.booking.project.service.interfaces.INotificationTypeStatusService;
import org.apache.coyote.Response;
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
    @Autowired
    private INotificationTypeStatusService notificationTypeStatusService;


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
    @PreAuthorize("hasRole('HOST')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> markNotificationAsRead(@PathVariable("id") Long id){
        NotificationForHostDTO notificationForHostDTO = notificationForHostService.markAsRead(id);
        return new ResponseEntity<NotificationForHostDTO>(notificationForHostDTO,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('HOST')")
    @GetMapping(value="/hostNotificationStatus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHostNotificationsStatus(@PathVariable Long id){
        Collection<NotificationTypeStatus> notificationsTypeStatus = notificationTypeStatusService.findByUser(id);
        return new ResponseEntity<Collection<NotificationTypeStatus>>(notificationsTypeStatus, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('HOST')")
    @PutMapping(value="/changeNotificationStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeHostNotificationStatus(@RequestBody NotificationTypeStatusDTO notificationTypeStatusDTO){
        notificationTypeStatusService.changeNotificationStatus(notificationTypeStatusDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
