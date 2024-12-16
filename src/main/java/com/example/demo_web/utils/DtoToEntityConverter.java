package com.example.demo_web.utils;

import com.example.demo_web.dto.request_body.HotelContractRequestDto;
import com.example.demo_web.model.HotelContract;
import com.example.demo_web.model.RoomType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to convert DTOs to entity objects.
 * This class provides methods to convert {@link HotelContractRequestDto} and {@link HotelContractRequestDto.RoomTypeDto}
 * to their corresponding {@link HotelContract} and {@link RoomType} entities.
 */
public class DtoToEntityConverter {

    /**
     * Converts a {@link HotelContractRequestDto} to a {@link HotelContract} entity.
     *
     * @param requestDto the HotelContractRequestDto to convert
     * @return the corresponding HotelContract entity
     */
    public static HotelContract toEntity(HotelContractRequestDto requestDto) {
        HotelContract hotelContract = new HotelContract();
        hotelContract.setHotelName(requestDto.getHotelName());
        hotelContract.setStartDate(requestDto.getStartDate());
        hotelContract.setEndDate(requestDto.getEndDate());
        hotelContract.setMarkup(requestDto.getMarkup());

        // Convert the list of room types from the DTO to entities
        List<RoomType> roomTypes = requestDto.getRoomTypes().stream()
                                             .map(roomTypeDto -> toEntity(roomTypeDto, hotelContract))
                                             .collect(Collectors.toList());

        hotelContract.setRoomTypes(roomTypes);
        return hotelContract;
    }

    /**
     * Converts a {@link HotelContractRequestDto.RoomTypeDto} to a {@link RoomType} entity.
     *
     * @param roomTypeDto the RoomTypeDto to convert
     * @param hotelContract the associated HotelContract entity (foreign key)
     * @return the corresponding RoomType entity
     */
    public static RoomType toEntity(HotelContractRequestDto.RoomTypeDto roomTypeDto, HotelContract hotelContract) {
        RoomType roomType = new RoomType();
        roomType.setRoomtypeName(roomTypeDto.getRoomTypeName());
        roomType.setBaseprice(roomTypeDto.getBasePrice());
        roomType.setNumberofRooms(roomTypeDto.getNumberOfRooms());
        roomType.setMaxAdults(roomTypeDto.getMaxAdults());
        roomType.setHotelContract(hotelContract); // Set the hotelContract (foreign key)
        return roomType;
    }
}
