package com.example.demo_web.utils;

import com.example.demo_web.dto.HotelContractDto;
import com.example.demo_web.model.HotelContract;

/**
 * Utility class to map between {@link HotelContract} entity and {@link HotelContractDto}.
 * This class provides methods to convert a HotelContract entity to a DTO and vice versa.
 */
public class ContractMapper {

    /**
     * Converts a {@link HotelContract} entity to a {@link HotelContractDto}.
     *
     * @param contract the HotelContract entity to convert
     * @return the corresponding HotelContractDto
     */
    public static HotelContractDto entityToDto(HotelContract contract) {
        HotelContractDto dto = new HotelContractDto();
        dto.setContractId(contract.getContractId());
        dto.setHotelName(contract.getHotelName());
        dto.setStartDate(contract.getStartDate());
        dto.setEndDate(contract.getEndDate());
        dto.setMarkup(contract.getMarkup());
        return dto;
    }

    /**
     * Converts a {@link HotelContractDto} to a {@link HotelContract} entity.
     *
     * @param dto the HotelContractDto to convert
     * @return the corresponding HotelContract entity
     */
    public static HotelContract dtoToEntity(HotelContractDto dto) {
        HotelContract contract = new HotelContract();
        contract.setContractId(dto.getContractId());
        contract.setHotelName(dto.getHotelName());
        contract.setStartDate(dto.getStartDate());
        contract.setEndDate(dto.getEndDate());
        contract.setMarkup(dto.getMarkup());
        return contract;
    }
}
