package com.example.demo_web.utils;

import com.example.demo_web.dto.HotelContractDto;
import com.example.demo_web.dto.request_body.HotelContractRequestDto;
import com.example.demo_web.dto.response_body.RoomTypeSearchResultDto;
import com.example.demo_web.model.HotelContract;
import com.example.demo_web.model.RoomType;

import java.util.List;
import java.util.stream.Collectors;

public class EntityToDtoConverter {

    // Convert HotelContract entity to HotelContractDto
    public static HotelContractDto toDto(HotelContract hotelContract) {
        HotelContractDto hotelContractDto = new HotelContractDto();
        hotelContractDto.setContractId(hotelContract.getContractId());
        hotelContractDto.setHotelName(hotelContract.getHotelName());
        hotelContractDto.setStartDate(hotelContract.getStartDate());
        hotelContractDto.setEndDate(hotelContract.getEndDate());
        hotelContractDto.setMarkup(hotelContract.getMarkup());

        // Convert RoomType entities to RoomTypeDto (from HotelContractDto)
        List<HotelContractDto.RoomTypeDto> roomTypeDtos = hotelContract.getRoomTypes().stream()
                                                                       .map(EntityToDtoConverter::toDto)  // Convert each RoomType entity to RoomTypeDto
                                                                       .collect(Collectors.toList());

        hotelContractDto.setRoomTypes(roomTypeDtos);
        return hotelContractDto;
    }

    // Convert RoomType entity to HotelContractDto.RoomTypeDto
    public static HotelContractDto.RoomTypeDto toDto(RoomType roomType) {
        HotelContractDto.RoomTypeDto roomTypeDto = new HotelContractDto.RoomTypeDto();
        roomTypeDto.setRoomTypeId(roomType.getRoomtypeId());
        roomTypeDto.setRoomTypeName(roomType.getRoomtypeName());
        roomTypeDto.setBasePrice(roomType.getBaseprice());
        roomTypeDto.setNumberOfRooms(roomType.getNumberofRooms());
        roomTypeDto.setMaxAdults(roomType.getMaxAdults());
        return roomTypeDto;
    }

    // Convert RoomType entity to HotelContractRequestDto.RoomTypeDto (If needed for requests)
    public static HotelContractRequestDto.RoomTypeDto toRequestDto(RoomType roomType) {
        HotelContractRequestDto.RoomTypeDto roomTypeDto = new HotelContractRequestDto.RoomTypeDto();
        roomTypeDto.setRoomTypeName(roomType.getRoomtypeName());
        roomTypeDto.setBasePrice(roomType.getBaseprice());
        roomTypeDto.setNumberOfRooms(roomType.getNumberofRooms());
        roomTypeDto.setMaxAdults(roomType.getMaxAdults());
        return roomTypeDto;
    }

    public static RoomTypeSearchResultDto RoomTypeEntityToRoomTypeSearchResultDto( RoomType roomType) {
        RoomTypeSearchResultDto roomTypeSearchResultDto = new RoomTypeSearchResultDto();
        roomTypeSearchResultDto.setHotelName( roomType.getHotelContract().getHotelName() );
        roomTypeSearchResultDto.setRoomType( roomType.getRoomtypeName() );
        //a variable to store markup
        float markup = roomType.getHotelContract().getMarkup();
        float basePrice = roomType.getBaseprice();
        roomTypeSearchResultDto.setFinalPrice(basePrice+(basePrice*markup));
        return roomTypeSearchResultDto;
    }
}
