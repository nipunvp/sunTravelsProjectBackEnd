package com.example.demo_web.controllerTest.HotelContractController;

import com.example.demo_web.controller.HotelContractController;
import com.example.demo_web.dto.HotelContractDto;
import com.example.demo_web.dto.request_body.HotelContractRequestDto;
import com.example.demo_web.exception.ContractNotFoundException;
import com.example.demo_web.service.HotelContractService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link HotelContractController}.
 *
 * These tests cover the basic operations of the {@link HotelContractController},
 * such as retrieving, adding, and deleting contracts. The tests use Mockito
 * to mock the dependencies and ensure the controller behaves as expected.
 */
class HotelContractControllerTest {

    private final HotelContractService contractService = mock(HotelContractService.class);
    private final HotelContractController controller = new HotelContractController(contractService);

    /**
     * Tests the {@link HotelContractController#getContracts()} method.
     * Verifies that the controller returns a list of contracts with an HTTP status of 200 (OK).
     */
    @Test
    void testGetContracts() {
        // Arrange
        List<HotelContractDto> mockContracts = new ArrayList<>();
        mockContracts.add(new HotelContractDto(
                1L,
                "Hotel A",
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 31),
                20.0f,
                new ArrayList<>()
        ));
        when(contractService.getContracts()).thenReturn(mockContracts);

        // Act
        ResponseEntity<List<HotelContractDto>> response = controller.getContracts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockContracts, response.getBody());
        verify(contractService, times(1)).getContracts();
    }

    /**
     * Tests the {@link HotelContractController#addContract(HotelContractRequestDto)} method.
     * Verifies that the controller successfully adds a new contract and returns a 201 (CREATED) response.
     */
    @Test
    void testAddContract() {
        // Arrange
        HotelContractRequestDto requestDto = new HotelContractRequestDto(
                "Hotel A",
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 31),
                20.0f,
                new ArrayList<>()
        );
        HotelContractDto responseDto = new HotelContractDto(
                1L,
                "Hotel A",
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 31),
                20.0f,
                new ArrayList<>()
        );
        when(contractService.addContract(requestDto)).thenReturn(responseDto);

        // Act
        ResponseEntity<HotelContractDto> response = controller.addContract(requestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(contractService, times(1)).addContract(requestDto);
    }

    /**
     * Tests the {@link HotelContractController#deleteContract(Long)} method for a successful deletion.
     * Verifies that the controller returns an HTTP status of 200 (OK) when a contract is deleted successfully.
     */
    @Test
    void testDeleteContract_Success() {
        // Arrange
        Long contractId = 1L;
        doNothing().when(contractService).deleteContract(contractId);

        // Act
        ResponseEntity<Void> response = controller.deleteContract(contractId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(contractService, times(1)).deleteContract(contractId);
    }

    /**
     * Tests the {@link HotelContractController#deleteContract(Long)} method when the contract is not found.
     * Verifies that the controller returns a 404 (NOT FOUND) status when a contract is not found for deletion.
     */
    @Test
    void testDeleteContract_NotFound() {
        // Arrange
        Long contractId = 1L;
        doThrow(new ContractNotFoundException("Contract not found")).when(contractService).deleteContract(contractId);

        // Act
        ResponseEntity<Void> response = controller.deleteContract(contractId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(contractService, times(1)).deleteContract(contractId);
    }
}
