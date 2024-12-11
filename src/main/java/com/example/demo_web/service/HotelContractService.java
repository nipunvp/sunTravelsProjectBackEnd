package com.example.demo_web.service;

import com.example.demo_web.dto.HotelContractDto;
import com.example.demo_web.dto.request_body.HotelContractRequestDto;
import com.example.demo_web.exception.ContractNotFoundException;
import com.example.demo_web.exception.InvalidContractException;
import com.example.demo_web.model.HotelContract;
import com.example.demo_web.repository.HotelContractRepository;
import com.example.demo_web.utils.DtoToEntityConverter;
import com.example.demo_web.utils.EntityToDtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelContractService
{

   public static final Logger logger = LoggerFactory.getLogger( HotelContractService.class );

    private final HotelContractRepository contractRepository;


    @Autowired
    public HotelContractService(HotelContractRepository contractRepository)
    {
        this.contractRepository = contractRepository;
    }

    public List<HotelContractDto> getContracts()
    {
        logger.info( "Getting all contracts" );

        List<HotelContract> contracts = contractRepository.findAll();

        List<HotelContractDto> contractDtos = contracts.stream().map(EntityToDtoConverter::toDto).collect(Collectors.toList());

        return contractDtos;
    }


    public void deleteContract( Long contractId )
    {
        Optional<HotelContract> contractOptional = contractRepository.findById( contractId );
        if(contractOptional.isPresent())
        {
            contractRepository.deleteById( contractId );
        }
        else
        {
            throw new ContractNotFoundException( "Contract with Id" + contractId + " was not found" );
        }
    }



    public HotelContractDto addContract( HotelContractRequestDto contractRequestDto) {
        // Convert DTO to entity
        validateContract(contractRequestDto);

        HotelContract hotelContract = DtoToEntityConverter.toEntity(contractRequestDto);

        // Save the entity
        hotelContract = contractRepository.save(hotelContract);

        // Convert the saved entity back to DTO
        return EntityToDtoConverter.toDto(hotelContract);
    }


    //ValidateContract code which is a helper function we put inside addContracts
    private void validateContract(HotelContractRequestDto contractRequestDto) {
        LocalDate startDate = contractRequestDto.getStartDate();
        LocalDate endDate = contractRequestDto.getEndDate();
        String hotelName = contractRequestDto.getHotelName();
        float markup = contractRequestDto.getMarkup();
        List<HotelContractRequestDto.RoomTypeDto> roomTypes = contractRequestDto.getRoomTypes();

        // Check if hotelName, startDate, endDate, or markup is null/empty
        if (hotelName == null || hotelName.trim().isEmpty()) {
            throw new InvalidContractException("Hotel name cannot be empty.");
        }
        if (startDate == null) {
            throw new InvalidContractException("Start date cannot be empty.");
        }
        if (endDate == null) {
            throw new InvalidContractException("End date cannot be empty.");
        }
        if (markup < 0) {
            throw new InvalidContractException("Markup cannot be negative.");
        }

        // Rule 1: Start date should be before end date
        if (!startDate.isBefore(endDate)) {
            throw new InvalidContractException("Start date must be before the end date.");
        }

        // Rule 2: End date should be after the current date
        if (endDate.isBefore(LocalDate.now()) || endDate.isEqual(LocalDate.now())) {
            throw new InvalidContractException("End date must be after the current date.");
        }

        // Rule 3: Markup should be less than 1
        if (markup >= 1) {
            throw new InvalidContractException("Markup must be less than 1.");
        }

        // Rule 4: There should be at least one room type
        if (roomTypes == null || roomTypes.isEmpty()) {
            throw new InvalidContractException("There must be at least one room type.");
        }

        // Rule 5: Check if a contract with the same hotel name already exists
        List<HotelContract> existingContracts = contractRepository.findAll(); // Get all contracts
        boolean isHotelNameDuplicate = existingContracts.stream()
                                                        .anyMatch(contract -> contract.getHotelName().equalsIgnoreCase(hotelName)); // Check if any contract has the same hotel name

        if (isHotelNameDuplicate) {
            throw new InvalidContractException("A contract with this hotel name already exists.");
        }
    }




}
