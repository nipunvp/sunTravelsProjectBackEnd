package com.example.demo_web.controller;
import com.example.demo_web.dto.HotelContractDto;
import com.example.demo_web.dto.request_body.HotelContractRequestDto;
import com.example.demo_web.exception.ContractNotFoundException;
import com.example.demo_web.service.HotelContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contract")
public class HotelContractController
{

    private final HotelContractService contractService;

    @Autowired
    public HotelContractController(HotelContractService contractService)
    {
        this.contractService = contractService;
    }

    //ResponseEntity

    @GetMapping
    //Using ResponseEntity
    public ResponseEntity<List<HotelContractDto>> getContracts()
    {
        return new ResponseEntity<>( contractService.getContracts(), HttpStatus.OK );
    }

    @PostMapping("/addContract")
    public ResponseEntity<HotelContractDto> addContract(@RequestBody HotelContractRequestDto dto)
    {
        return new ResponseEntity<>( contractService.addContract( dto ), HttpStatus.CREATED );
    }





    @DeleteMapping("/{contractId}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long contractId)
    {
        try{
            contractService.deleteContract( contractId );
            return new ResponseEntity<>( HttpStatus.OK );
        }
        catch( ContractNotFoundException e )
        {
            //404
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }

}
