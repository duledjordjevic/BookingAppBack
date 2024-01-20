package com.booking.project.unit.controller;

import com.booking.project.dto.*;
import com.booking.project.model.Reservation;
import com.booking.project.model.User;
import com.booking.project.model.enums.ReservationMethod;
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

import java.time.LocalDate;
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
    private String guestMail = "nmaric1912@gmail.com";

    private String hostMail = "n.maric1912@gmail.com";

//    @BeforeEach
//    public void login() {
//        UserCredentialsDTO user = new UserCredentialsDTO();
//        user.setEmail("nmaric1912@gmail.com");
//        user.setPassword("123");
//        ResponseEntity<JwtDTO> responseEntity = restTemplate.exchange("/api/auth/login",
//                HttpMethod.POST,
//                new HttpEntity<>(user),
//                new ParameterizedTypeReference<JwtDTO>() {
//                });
//        this.jwt = "Bearer " + responseEntity.getBody().getJwt();
//    }
    private HttpHeaders getHttpHeaders(String role){
        UserCredentialsDTO user = new UserCredentialsDTO();
        if(role.equals("GUEST")){
            user.setEmail(guestMail);
        }else {
            user.setEmail(hostMail);
        }
        user.setPassword("123");
        ResponseEntity<JwtDTO> responseEntity = restTemplate.exchange("/api/auth/login",
                HttpMethod.POST,
                new HttpEntity<>(user),
                new ParameterizedTypeReference<JwtDTO>() {
                });
        this.jwt = "Bearer " + responseEntity.getBody().getJwt();

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
                new HttpEntity<>( getHttpHeaders("HOST")),
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
                new HttpEntity<>( getHttpHeaders("HOST")),
                new ParameterizedTypeReference<ReservationDTO>() {
                });

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(ReservationStatus.DECLINED, responseEntity.getBody().getStatus());
    }

    @Test
    @Rollback
    @DisplayName("Should accepted reservation status When making PUT request to endpoint - /api/reservations/{id}/{reservationStatus}")
    public void shouldReturnInternalServerErrorForUpdateReservationStatus() {
        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations/10/ACCEPTED",
                HttpMethod.PUT,
                new HttpEntity<>( getHttpHeaders("HOST")),
                new ParameterizedTypeReference<ReservationDTO>() {
                });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    @Rollback
    @DisplayName("Should create reservation When making POST request to endpoint - /api/reservations")
    public void shouldCreateReservation(){
        CreateReservationDTO createReservationDTO = new CreateReservationDTO(LocalDate.of(2024,1,21),
                LocalDate.of(2024,1,22),4,1L,1L);

        ResponseEntity<ReservationMethod> responseEntity = restTemplate.exchange("/api/reservations",
                HttpMethod.POST,
                new HttpEntity<>(createReservationDTO,getHttpHeaders("GUEST")),
                new ParameterizedTypeReference<ReservationMethod>() {
                });

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    @Test
    @Rollback
    @DisplayName("Should create reservation When making POST request to endpoint - /api/reservations")
    public void shouldReturnInternalServerErrorForCreateReservation(){
        CreateReservationDTO createReservationDTO = new CreateReservationDTO(LocalDate.of(2024,1,21),
                LocalDate.of(2024,1,22),7,1L,1L);

        ResponseEntity<?> responseEntity = restTemplate.exchange("/api/reservations",
                HttpMethod.POST,
                new HttpEntity<>(createReservationDTO,getHttpHeaders("GUEST")),
                new ParameterizedTypeReference<>() {
                });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

}
