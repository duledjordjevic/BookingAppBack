package com.booking.project.controller;

import com.booking.project.dto.JwtDTO;
import com.booking.project.dto.ReservationDTO;
import com.booking.project.dto.UserCredentialsDTO;
import com.booking.project.dto.UserInfoDTO;
import com.booking.project.model.Reservation;
import com.booking.project.model.User;
import com.booking.project.model.enums.ReservationStatus;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(
//        locations = "classpath:application-test.properties")
//@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration")
@ActiveProfiles("test")
@Transactional
public class ReservationControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String jwt;

    @BeforeEach
    public void login() {
        UserCredentialsDTO user = new UserCredentialsDTO();
        user.setEmail("n.maric1912@gmail.com");
        user.setPassword("123");
        ResponseEntity<JwtDTO> responseEntity = restTemplate.exchange("/api/auth/login",
                HttpMethod.POST,
                new HttpEntity<>(user),
                new ParameterizedTypeReference<JwtDTO>() {
                });
        this.jwt = "Bearer " + responseEntity.getBody().getJwt();
    }
    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwt);
        return headers;
    }

    @Test
    @Rollback
    @DisplayName("Should accepted reservation status When making PUT request to endpoint - /api/reservations/{id}/{reservationStatus}")
    public void shouldAcceptedReservationStatus() {
        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations/1/ACCEPTED",
                HttpMethod.PUT,
                new HttpEntity<>( getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {
                });

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(ReservationStatus.ACCEPTED, responseEntity.getBody().getStatus());
    }

    @Test
    @Rollback
    @DisplayName("Should accepted reservation status When making PUT request to endpoint - /api/reservations/{id}/{reservationStatus}")
    public void shouldDeclinedReservationStatus() {
        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations/2/DECLINED",
                HttpMethod.PUT,
                new HttpEntity<>( getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {
                });

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(ReservationStatus.DECLINED, responseEntity.getBody().getStatus());
    }

    @Test
    @Rollback
    @DisplayName("Should accepted reservation status When making PUT request to endpoint - /api/reservations/{id}/{reservationStatus}")
    public void shouldReturnInternalServerError() {
        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations/10/ACCEPTED",
                HttpMethod.PUT,
                new HttpEntity<>( getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {
                });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

}
