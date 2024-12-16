package com.example.demo_web.controllerTest.RoomTypeController;

import com.example.demo_web.controller.RoomTypeController;
import com.example.demo_web.dto.request_body.HotelSearchRequestDto;
import com.example.demo_web.dto.response_body.NewRoomTypeSearchResultDto;
import com.example.demo_web.dto.response_body.RoomTypeSearchResultDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration tests for {@link RoomTypeController}.
 * <p>
 * This class contains integration tests for searching room types based on user inputs, ensuring that the controller
 * behaves as expected when interacting with the backend system. The tests include sending POST requests with search data
 * and verifying the response.
 * </p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomTypeControllerIT {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Returns the base URL of the contract API.
     *
     * @return the base URL as a string.
     */
    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/v1/contract";
    }

    /**
     * Tests the search functionality for room types.
     * <p>
     * This test sends a POST request to search for room types based on user selection and booking details.
     * It checks that the response status is OK and that the response body contains valid room types.
     * </p>
     */
    @Test
    void testSearchContracts() {
        // Arrange
        LocalDate checkInDate = LocalDate.of(2023, 12, 1);
        int numberOfNights = 5;
        List<HotelSearchRequestDto.Selection> selections = new ArrayList<>();
        selections.add(new HotelSearchRequestDto.Selection(2, 3));

        HotelSearchRequestDto searchDto = new HotelSearchRequestDto(checkInDate, numberOfNights, selections);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HotelSearchRequestDto> request = new HttpEntity<>(searchDto, headers);

        // Act
        ResponseEntity<RoomTypeSearchResultDto[]> response = restTemplate.exchange(
                getBaseUrl() + "/search", HttpMethod.POST, request, RoomTypeSearchResultDto[].class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        List<RoomTypeSearchResultDto> roomTypes = Arrays.asList(response.getBody());
        assertNotNull(roomTypes);
    }

    /**
     * Tests the search functionality for room types with a new API endpoint.
     * <p>
     * This test sends a POST request to a new endpoint for searching room types based on user selection and booking details.
     * It checks that the response status is OK and that the response body contains valid room types.
     * </p>
     */
    @Test
    void testSearchContractsNew() {
        // Arrange
        LocalDate checkInDate = LocalDate.of(2027, 12, 1);
        int numberOfNights = 5;
        List<HotelSearchRequestDto.Selection> selections = new ArrayList<>();
        selections.add(new HotelSearchRequestDto.Selection(2, 4));

        HotelSearchRequestDto searchDto = new HotelSearchRequestDto(checkInDate, numberOfNights, selections);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HotelSearchRequestDto> request = new HttpEntity<>(searchDto, headers);

        // Act
        ResponseEntity<NewRoomTypeSearchResultDto[]> response = restTemplate.exchange(
                getBaseUrl() + "/searchNew", HttpMethod.POST, request, NewRoomTypeSearchResultDto[].class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        List<NewRoomTypeSearchResultDto> roomTypes = Arrays.asList(response.getBody());
        assertNotNull(roomTypes);
    }
}
