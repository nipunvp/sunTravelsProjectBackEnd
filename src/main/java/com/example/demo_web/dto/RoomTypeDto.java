package com.example.demo_web.dto;

public class RoomTypeDto {
    private Long roomTypeId;
    private String roomTypeName;
    private float basePrice;
    private int numberOfRooms;
    private int maxAdults;

    // Getters and Setters...

//    public RoomTypeDto( Long roomTypeId, String roomTypeName, float basePrice, int numberOfRooms, int maxAdults )
//    {
//        this.roomTypeId = roomTypeId;
//        this.roomTypeName = roomTypeName;
//        this.basePrice = basePrice;
//        this.numberOfRooms = numberOfRooms;
//        this.maxAdults = maxAdults;
//    }

    public Long getRoomTypeId()
    {
        return roomTypeId;
    }

    public void setRoomTypeId( Long roomTypeId )
    {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName()
    {
        return roomTypeName;
    }

    public void setRoomTypeName( String roomTypeName )
    {
        this.roomTypeName = roomTypeName;
    }

    public float getBasePrice()
    {
        return basePrice;
    }

    public void setBasePrice( float basePrice )
    {
        this.basePrice = basePrice;
    }

    public int getNumberOfRooms()
    {
        return numberOfRooms;
    }

    public void setNumberOfRooms( int numberOfRooms )
    {
        this.numberOfRooms = numberOfRooms;
    }

    public int getMaxAdults()
    {
        return maxAdults;
    }

    public void setMaxAdults( int maxAdults )
    {
        this.maxAdults = maxAdults;
    }

}

