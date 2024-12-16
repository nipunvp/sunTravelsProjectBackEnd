package com.example.demo_web.controllerTest.HotelContractController;

import com.example.demo_web.controller.HotelContractController;
import com.example.demo_web.dto.HotelContractDto;
import com.example.demo_web.dto.request_body.HotelContractRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration tests for {@link HotelContractController}. This class contains tests for the
 * CRUD operations on hotel contracts such as retrieving, adding, and deleting contracts.
 * The tests use a random port and the {@link RestTemplate} to send HTTP requests to the API.
 *
 * The tests are transactional, ensuring changes to the database are rolled back after each test.
 *
 * @author [Your Name]
 * @version 1.0
 */
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HotelContractControllerIT {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Constructs the base URL for the Hotel Contract API using the local server port.
     *
     * @return the base URL as a string.
     */
    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/v1/contract";
    }

    /**
     * Test method for retrieving all hotel contracts via GET request.
     * Sends a GET request to the "/api/v1/contract" endpoint and validates the response.
     *
     * Asserts that the response status is OK and that the response body is not null.
     */
    @Test
    void testGetContracts() {
        // Act
        ResponseEntity<HotelContractDto[]> response = restTemplate.getForEntity(getBaseUrl(), HotelContractDto[].class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        List<HotelContractDto> contracts = Arrays.asList(response.getBody());
        assertNotNull(contracts);
    }

    /**
     * Test method for adding a new hotel contract via POST request.
     * Sends a POST request to the "/api/v1/contract/addContract" endpoint with a sample hotel contract.
     *
     * Asserts that the response status is CREATED, the response body is not null,
     * and the hotel name in the response matches the provided name.
     */
    @Test
    void testAddContract() {
        // Arrange
        HotelContractRequestDto.RoomTypeDto roomType = new HotelContractRequestDto.RoomTypeDto();
        roomType.setRoomTypeName("Deluxe Room");
        roomType.setBasePrice(100.0f);
        roomType.setNumberOfRooms(10);
        roomType.setMaxAdults(2);

        List<HotelContractRequestDto.RoomTypeDto> roomTypes = new ArrayList<>();
        roomTypes.add(roomType);

        HotelContractRequestDto requestDto = new HotelContractRequestDto(
                "Hotel A",
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2025, 12, 31),
                0.2f,
                roomTypes
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HotelContractRequestDto> request = new HttpEntity<>(requestDto, headers);

        // Act
        ResponseEntity<HotelContractDto> response = restTemplate.postForEntity(getBaseUrl() + "/addContract", request, HotelContractDto.class);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Hotel A", response.getBody().getHotelName());
    }

    /**
     * Test method for deleting an existing hotel contract via DELETE request.
     * Sends a DELETE request to the "/api/v1/contract/{contractId}" endpoint.
     *
     * Asserts that the response status is OK.
     */
    @Test
    void testDeleteContract() {
        // Arrange
        Long contractId = 22L; // Ensure this ID exists in the test database

        // Act
        ResponseEntity<Void> response = restTemplate.exchange(getBaseUrl() + "/" + contractId, HttpMethod.DELETE, null, Void.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
