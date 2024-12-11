package com.example.demo_web.utils;

import com.example.demo_web.dto.HotelContractDto;
import com.example.demo_web.model.HotelContract;

public class ContractMapper
{
    public static HotelContractDto entityToDto( HotelContract contract)
    {
        HotelContractDto dto = new HotelContractDto();
        dto.setContractId( contract.getContractId() );
        dto.setHotelName( contract.getHotelName() );
        dto.setStartDate( contract.getStartDate() );
        dto.setEndDate( contract.getEndDate() );
        dto.setMarkup( contract.getMarkup() );
        return dto;
    }

    public static HotelContract dtoToEntity(HotelContractDto dto)
    {
        HotelContract contract = new HotelContract();
        contract.setContractId( dto.getContractId() );
        contract.setHotelName( dto.getHotelName() );
        contract.setStartDate( dto.getStartDate() );
        contract.setEndDate( dto.getEndDate() );
        contract.setMarkup( dto.getMarkup() );
        return contract;
    }
}
