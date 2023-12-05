package com.booking.project.controller;

import com.booking.project.dto.UserCredentialsDTO;
import com.booking.project.dto.UserRegisterDTO;
import com.booking.project.exception.ResourceConflictException;
import com.booking.project.model.Guest;
import com.booking.project.model.Host;
import com.booking.project.model.User;
import com.booking.project.model.enums.UserType;
import com.booking.project.security.jwt.JwtTokenUtil;
import com.booking.project.service.GuestService;
import com.booking.project.service.HostService;
import com.booking.project.service.UserDetailsServiceImpl;
import com.booking.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping()
    public User login(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(userCredentialsDTO.getEmail(),
                userCredentialsDTO.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        String token = jwtTokenUtil.generateToken(userCredentialsDTO.getEmail(), userDetailsService.loadUserByUsername(userCredentialsDTO.getEmail()));
        User user = new User(userCredentialsDTO);

        user.setJwt(token);
        return user;
    }

}
