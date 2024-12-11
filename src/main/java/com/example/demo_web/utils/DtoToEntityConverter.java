package com.example.demo_web.utils;

import com.example.demo_web.dto.request_body.HotelContractRequestDto;
import com.example.demo_web.model.HotelContract;
import com.example.demo_web.model.RoomType;

import java.util.List;
import java.util.stream.Collectors;

public class DtoToEntityConverter {

    // Convert HotelContractRequestDto to HotelContract entity
    public static HotelContract toEntity(HotelContractRequestDto requestDto) {
        HotelContract hotelContract = new HotelContract();
        hotelContract.setHotelName(requestDto.getHotelName());
        hotelContract.setStartDate(requestDto.getStartDate());
        hotelContract.setEndDate(requestDto.getEndDate());
        hotelContract.setMarkup(requestDto.getMarkup());

        List<RoomType> roomTypes = requestDto.getRoomTypes().stream()
                                             .map(roomTypeDto -> toEntity(roomTypeDto, hotelContract))
                                             .collect(Collectors.toList());

        hotelContract.setRoomTypes(roomTypes);
        return hotelContract;
    }

    // Convert RoomTypeDto to RoomType entity
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
