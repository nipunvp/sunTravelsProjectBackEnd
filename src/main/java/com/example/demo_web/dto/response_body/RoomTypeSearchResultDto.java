package com.example.demo_web.dto.response_body;

public class RoomTypeSearchResultDto
{
    private String hotelName;
    private String roomType;
    private float finalPrice;

    public RoomTypeSearchResultDto()
    {
    }

    public String getHotelName()
    {
        return hotelName;
    }

    public void setHotelName( String hotelName )
    {
        this.hotelName = hotelName;
    }

    public String getRoomType()
    {
        return roomType;
    }

    public void setRoomType( String roomType )
    {
        this.roomType = roomType;
    }

    public float getFinalPrice()
    {
        return finalPrice;
    }

    public void setFinalPrice( float finalPrice )
    {
        this.finalPrice = finalPrice;
    }
}
