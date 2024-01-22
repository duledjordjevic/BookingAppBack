package com.booking.project.unit.controller;

import com.booking.project.dto.*;
import com.booking.project.model.Accommodation;
import com.booking.project.model.enums.ReservationMethod;
import com.booking.project.model.enums.ReservationStatus;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration")
@ActiveProfiles("test")
@Transactional
public class AccommodationControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private String jwt;
    private String guestMail = "nmaric1912@gmail.com";

    private String hostMail = "n.maric1912@gmail.com";

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
    public void shouldAddPriceList() {
        List<IntervalPriceDTO> intervalPriceDTOS = new ArrayList<>();
        IntervalPriceDTO intervalPriceDTO1 = new IntervalPriceDTO
                (LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 3), 100.0);
        IntervalPriceDTO intervalPriceDTO2 = new IntervalPriceDTO
                (LocalDate.of(2024, 5, 10), LocalDate.of(2024, 5, 15), 100.0);

        intervalPriceDTOS.add(intervalPriceDTO1);
        intervalPriceDTOS.add(intervalPriceDTO2);

        ResponseEntity<Integer> responseEntity = restTemplate.exchange("/api/accommodations/priceList/1",
                HttpMethod.POST,
                new HttpEntity<>(intervalPriceDTOS,getHttpHeaders("HOST")),
                new ParameterizedTypeReference<Integer>() {
                });

        Integer result = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(result);
        assertEquals(9, result);
    }

    @Test
    @Rollback
    public void shouldNotAddPriceList() {
        List<IntervalPriceDTO> intervalPriceDTOS = new ArrayList<>();
        IntervalPriceDTO intervalPriceDTO1 = new IntervalPriceDTO
                (LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 3), 100.0);
        IntervalPriceDTO intervalPriceDTO2 = new IntervalPriceDTO
                (LocalDate.of(2024, 5, 10), LocalDate.of(2024, 5, 15), 100.0);

        intervalPriceDTOS.add(intervalPriceDTO1);
        intervalPriceDTOS.add(intervalPriceDTO2);

        ResponseEntity<Integer> responseEntity = restTemplate.exchange("/api/accommodations/priceList/5",
                HttpMethod.POST,
                new HttpEntity<>(intervalPriceDTOS,getHttpHeaders("HOST")),
                new ParameterizedTypeReference<Integer>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
    @Test
    @Rollback
    public void shouldGetIntervalPrices() {
        ResponseEntity<List<IntervalPriceDTO>> responseEntity = restTemplate.exchange("/api/accommodations/intervalPrices/1",
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders("HOST")),
                new ParameterizedTypeReference<List<IntervalPriceDTO>>() {
                });

        List<IntervalPriceDTO> intervalPriceDTOS = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Rollback
    public void shouldNotGetIntervalPrices() {
        ResponseEntity<List<IntervalPriceDTO>> responseEntity = restTemplate.exchange("/api/accommodations/intervalPrices/5",
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders("HOST")),
                new ParameterizedTypeReference<List<IntervalPriceDTO>>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
