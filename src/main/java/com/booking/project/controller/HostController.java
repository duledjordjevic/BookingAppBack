package com.booking.project.controller;

import com.booking.project.dto.HostDTO;
import com.booking.project.model.Guest;
import com.booking.project.service.interfaces.IGuestService;
import com.booking.project.service.interfaces.IHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    @Autowired
    private IHostService hostService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<HostDTO>> getHosts(){
        Collection<HostDTO> hosts = hostService.findHosts();
        return new ResponseEntity<Collection<HostDTO>>(hosts, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HostDTO> getHost(@PathVariable("id") Long id){
        HostDTO hostDTO = hostService.findHost(id);
        if(hostDTO == null)     return new ResponseEntity<HostDTO>(hostDTO,HttpStatus.NOT_FOUND);

        return new ResponseEntity<HostDTO>(hostDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HostDTO> deleteGuest(@PathVariable("id") Long id){
        hostService.deleteById(id);
        return new ResponseEntity<HostDTO>(HttpStatus.NO_CONTENT);
    }
}
