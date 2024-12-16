package com.example.demo_web.controller;

import com.example.demo_web.dto.HotelContractDto;
import com.example.demo_web.dto.request_body.HotelContractRequestDto;
import com.example.demo_web.exception.ContractNotFoundException;
import com.example.demo_web.service.HotelContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing hotel contracts.
 *
 * <p>This controller provides endpoints to perform CRUD operations on hotel contracts,
 * including retrieving all contracts, adding a new contract, and deleting an existing contract.</p>
 */
@RestController
@RequestMapping("/api/v1/contract")
public class HotelContractController {

    private final HotelContractService contractService;

    /**
     * Constructs a {@code HotelContractController} with the specified service.
     *
     * @param contractService the service to handle hotel contract business logic.
     */
    @Autowired
    public HotelContractController(HotelContractService contractService) {
        this.contractService = contractService;
    }

    /**
     * Retrieves all hotel contracts.
     *
     * @return a {@link ResponseEntity} containing the list of {@link HotelContractDto} objects
     *         and a status of {@code HttpStatus.OK}.
     */
    @GetMapping
    public ResponseEntity<List<HotelContractDto>> getContracts() {
        return new ResponseEntity<>(contractService.getContracts(), HttpStatus.OK);
    }

    /**
     * Adds a new hotel contract.
     *
     * @param dto the request body containing the details of the hotel contract to be added.
     * @return a {@link ResponseEntity} containing the created {@link HotelContractDto} object
     *         and a status of {@code HttpStatus.CREATED}.
     */
    @PostMapping("/addContract")
    public ResponseEntity<HotelContractDto> addContract(@RequestBody HotelContractRequestDto dto) {
        return new ResponseEntity<>(contractService.addContract(dto), HttpStatus.CREATED);
    }

    /**
     * Deletes an existing hotel contract by its ID.
     *
     * @param contractId the ID of the contract to be deleted.
     * @return a {@link ResponseEntity} with a status of {@code HttpStatus.OK} if the contract
     *         was deleted successfully, or {@code HttpStatus.NOT_FOUND} if the contract
     *         was not found.
     */
    @DeleteMapping("/{contractId}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long contractId) {
        try {
            contractService.deleteContract(contractId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ContractNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
