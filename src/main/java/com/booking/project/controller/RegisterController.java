package com.booking.project.controller;

import com.booking.project.dto.UserRegisterDTO;
import com.booking.project.exception.ResourceConflictException;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import com.booking.project.model.User;
import com.booking.project.model.enums.UserType;
import com.booking.project.service.GuestService;
import com.booking.project.service.HostService;
import com.booking.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private GuestService guestService;
    @Autowired
    private HostService hostService;

    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody UserRegisterDTO userRegisterDTO, UriComponentsBuilder ucBuilder) throws Exception {
        User existUser = this.userService.findByEmail(userRegisterDTO.getEmail());

        if (existUser != null) {
            throw new ResourceConflictException(userRegisterDTO.getId(), "Username already exists");
        }
        User user = new User(userRegisterDTO);

        if(user.getUserType().equals(UserType.GUEST)){
            Guest guest = new Guest(userRegisterDTO);
            guest.setUser(user);
            guestService.save(guest);

        }else if(user.getUserType().equals(UserType.HOST)){
            Host host = new Host(userRegisterDTO);
            host.setUser(user);
            hostService.save(host);
        }

        User savedUser = this.userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
