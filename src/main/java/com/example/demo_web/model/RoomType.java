package com.example.demo_web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RoomType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomtypeId;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private HotelContract hotelContract;

    private String roomtypeName;
    private float baseprice;
    private int numberofRooms;
    private int maxAdults;

    public RoomType(){

    }

    public RoomType( long roomtypeId, HotelContract hotelContract, String roomtypeName, float baseprice, int numberofRooms, int maxAdults )
    {
        this.roomtypeId = roomtypeId;
        this.hotelContract = hotelContract;
        this.roomtypeName = roomtypeName;
        this.baseprice = baseprice;
        this.numberofRooms = numberofRooms;
        this.maxAdults = maxAdults;
    }

    public long getRoomtypeId()
    {
        return roomtypeId;
    }

    public void setRoomtypeId( long roomtypeId )
    {
        this.roomtypeId = roomtypeId;
    }

    public HotelContract getHotelContract()
    {
        return hotelContract;
    }

    public void setHotelContract( HotelContract hotelContract )
    {
        this.hotelContract = hotelContract;
    }

    public String getRoomtypeName()
    {
        return roomtypeName;
    }

    public void setRoomtypeName( String roomtypeName )
    {
        this.roomtypeName = roomtypeName;
    }

    public float getBaseprice()
    {
        return baseprice;
    }

    public void setBaseprice( float baseprice )
    {
        this.baseprice = baseprice;
    }

    public int getNumberofRooms()
    {
        return numberofRooms;
    }

    public void setNumberofRooms( int numberofRooms )
    {
        this.numberofRooms = numberofRooms;
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
