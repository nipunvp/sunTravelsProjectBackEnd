package com.example.demo_web.dto.response_body;

import com.example.demo_web.dto.RoomTypeDto;
import com.example.demo_web.model.RoomType;

import java.util.List;

public class NewRoomTypeSearchResultDto
{
    private String hotelName;
    private List<RoomTypeDto> rooms;

    public NewRoomTypeSearchResultDto( String hotelName, List<RoomTypeDto> rooms )
    {
        this.hotelName = hotelName;
        this.rooms = rooms;
    }

    public String getHotelName()
    {
        return hotelName;
    }

    public void setHotelName( String hotelName )
    {
        this.hotelName = hotelName;
    }

    public List<RoomTypeDto> getRooms()
    {
        return rooms;
    }

    public void setRooms( List<RoomTypeDto> rooms )
    {
        this.rooms = rooms;
    }
}
