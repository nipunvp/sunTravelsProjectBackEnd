package com.example.demo_web.dto.response_body;

import com.example.demo_web.dto.RoomTypeDto;
import java.util.List;

/**
 * DTO representing the search result of room types for a specific hotel.
 *
 * <p>This class encapsulates the hotel name and a list of available room types,
 * making it suitable for use in search results where hotels and their respective
 * room details are returned.</p>
 */
public class NewRoomTypeSearchResultDto {

    /**
     * The name of the hotel.
     */
    private String hotelName;

    /**
     * A list of room types available in the hotel.
     */
    private List<RoomTypeDto> rooms;

    /**
     * Constructs a {@code NewRoomTypeSearchResultDto} with the specified hotel name and room types.
     *
     * @param hotelName the name of the hotel.
     * @param rooms a list of available room types in the hotel.
     */
    public NewRoomTypeSearchResultDto(String hotelName, List<RoomTypeDto> rooms) {
        this.hotelName = hotelName;
        this.rooms = rooms;
    }

    /**
     * Retrieves the name of the hotel.
     *
     * @return the hotel name.
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Sets the name of the hotel.
     *
     * @param hotelName the name of the hotel.
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * Retrieves the list of available room types in the hotel.
     *
     * @return the list of room types.
     */
    public List<RoomTypeDto> getRooms() {
        return rooms;
    }

    /**
     * Sets the list of available room types in the hotel.
     *
     * @param rooms the list of room types.
     */
    public void setRooms(List<RoomTypeDto> rooms) {
        this.rooms = rooms;
    }
}
