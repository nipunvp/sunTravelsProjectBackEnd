package com.example.demo_web.service;

import com.example.demo_web.dto.HotelContractDto;
import com.example.demo_web.dto.request_body.HotelContractRequestDto;
import com.example.demo_web.exception.ContractNotFoundException;
import com.example.demo_web.model.HotelContract;
import com.example.demo_web.repository.HotelContractRepository;
import com.example.demo_web.utils.DtoToEntityConverter;
import com.example.demo_web.utils.EntityToDtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        HotelContract hotelContract = DtoToEntityConverter.toEntity(contractRequestDto);

        // Save the entity
        hotelContract = contractRepository.save(hotelContract);

        // Convert the saved entity back to DTO
        return EntityToDtoConverter.toDto(hotelContract);
    }




}
