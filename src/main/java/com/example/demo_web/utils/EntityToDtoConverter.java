package com.example.demo_web.utils;

import com.example.demo_web.dto.HotelContractDto;
import com.example.demo_web.dto.RoomTypeDto;
import com.example.demo_web.dto.request_body.HotelContractRequestDto;
import com.example.demo_web.dto.response_body.NewRoomTypeSearchResultDto;
import com.example.demo_web.dto.response_body.RoomTypeSearchResultDto;
import com.example.demo_web.model.HotelContract;
import com.example.demo_web.model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class for converting entities to Data Transfer Objects (DTOs).
 * This class provides static methods to convert {@link HotelContract} and {@link RoomType} entities
 * to corresponding DTOs for use in various layers of the application (such as request bodies, response bodies, etc.).
 *
 * @author [Your Name]
 * @version 1.0
 */
public class EntityToDtoConverter {

    /**
     * Converts a {@link HotelContract} entity to a {@link HotelContractDto}.
     *
     * @param hotelContract the {@link HotelContract} entity to convert.
     * @return the converted {@link HotelContractDto}.
     */
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

    /**
     * Converts a {@link RoomType} entity to a {@link HotelContractDto.RoomTypeDto}.
     *
     * @param roomType the {@link RoomType} entity to convert.
     * @return the converted {@link HotelContractDto.RoomTypeDto}.
     */
    public static HotelContractDto.RoomTypeDto toDto(RoomType roomType) {
        HotelContractDto.RoomTypeDto roomTypeDto = new HotelContractDto.RoomTypeDto();
        roomTypeDto.setRoomTypeId(roomType.getRoomtypeId());
        roomTypeDto.setRoomTypeName(roomType.getRoomtypeName());
        roomTypeDto.setBasePrice(roomType.getBaseprice());
        roomTypeDto.setNumberOfRooms(roomType.getNumberofRooms());
        roomTypeDto.setMaxAdults(roomType.getMaxAdults());
        return roomTypeDto;
    }

    /**
     * Converts a {@link RoomType} entity to a {@link HotelContractRequestDto.RoomTypeDto}.
     * This is typically used for request body conversion.
     *
     * @param roomType the {@link RoomType} entity to convert.
     * @return the converted {@link HotelContractRequestDto.RoomTypeDto}.
     */
    public static HotelContractRequestDto.RoomTypeDto toRequestDto(RoomType roomType) {
        HotelContractRequestDto.RoomTypeDto roomTypeDto = new HotelContractRequestDto.RoomTypeDto();
        roomTypeDto.setRoomTypeName(roomType.getRoomtypeName());
        roomTypeDto.setBasePrice(roomType.getBaseprice());
        roomTypeDto.setNumberOfRooms(roomType.getNumberofRooms());
        roomTypeDto.setMaxAdults(roomType.getMaxAdults());
        return roomTypeDto;
    }

    /**
     * Converts a {@link RoomType} entity to a {@link RoomTypeSearchResultDto}.
     * This method is used for converting RoomType entities into search result DTOs.
     *
     * @param roomType the {@link RoomType} entity to convert.
     * @return the converted {@link RoomTypeSearchResultDto}.
     */
    public static RoomTypeSearchResultDto RoomTypeEntityToRoomTypeSearchResultDto(RoomType roomType) {
        RoomTypeSearchResultDto roomTypeSearchResultDto = new RoomTypeSearchResultDto();
        roomTypeSearchResultDto.setHotelName(roomType.getHotelContract().getHotelName());
        roomTypeSearchResultDto.setRoomType(roomType.getRoomtypeName());

        // Calculate final price including markup
        float markup = roomType.getHotelContract().getMarkup();
        float basePrice = roomType.getBaseprice();
        roomTypeSearchResultDto.setFinalPrice(basePrice + (basePrice * markup));
        return roomTypeSearchResultDto;
    }

    /**
     * Converts a {@link Map} of rooms grouped by hotel name into a list of {@link NewRoomTypeSearchResultDto}.
     * This is used to format search results when multiple room types are grouped by hotel.
     *
     * @param roomsByHotel the map containing hotel names as keys and lists of {@link RoomType} entities as values.
     * @return a list of {@link NewRoomTypeSearchResultDto} representing the grouped room types.
     */
    public static List<NewRoomTypeSearchResultDto> HashMapToNewRoomTypeSearchResultDtoList(
            Map<String, List<RoomType>> roomsByHotel) {

        List<NewRoomTypeSearchResultDto> result = new ArrayList<>();

        for (Map.Entry<String, List<RoomType>> entry : roomsByHotel.entrySet()) {
            String hotelName = entry.getKey();
            List<RoomType> roomList = entry.getValue();

            // Convert each RoomType to RoomTypeDto using the provided method
            List<RoomTypeDto> roomTypeDtos = roomList.stream()
                                                     .map(EntityToDtoConverter::RoomTypeEntityToRoomTypeDto) // Use the helper method
                                                     .collect(Collectors.toList());

            // Create NewRoomTypeSearchResultDto
            NewRoomTypeSearchResultDto dto = new NewRoomTypeSearchResultDto(hotelName, roomTypeDtos);
            result.add(dto);
        }

        return result;
    }

    /**
     * Converts a {@link RoomType} entity to a {@link RoomTypeDto}.
     * This helper method is used in the conversion of room types for search results.
     *
     * @param roomType the {@link RoomType} entity to convert.
     * @return the converted {@link RoomTypeDto}.
     */
    public static RoomTypeDto RoomTypeEntityToRoomTypeDto(RoomType roomType) {
        RoomTypeDto roomTypeDto = new RoomTypeDto();
        roomTypeDto.setRoomTypeName(roomType.getRoomtypeName());
        roomTypeDto.setMaxAdults(roomType.getMaxAdults());

        // Calculate the final price including markup
        float markup = roomType.getHotelContract().getMarkup();
        roomTypeDto.setBasePrice((roomType.getBaseprice() * markup) + roomType.getBaseprice());
        roomTypeDto.setNumberOfRooms(roomType.getNumberofRooms());
        return roomTypeDto;
    }
}
