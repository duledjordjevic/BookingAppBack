package com.booking.project.controller;

import com.booking.project.dto.HostDTO;
import com.booking.project.model.Guest;
import com.booking.project.service.interfaces.IGuestService;
import com.booking.project.service.interfaces.IHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    @Autowired
    private IHostService hostService;
    @PreAuthorize("hasRole('HOST')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HostDTO> deleteHost(@PathVariable("id") Long id){
        hostService.deleteById(id);
        return new ResponseEntity<HostDTO>(HttpStatus.NO_CONTENT);
    }
}
