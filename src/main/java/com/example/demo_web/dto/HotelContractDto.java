package com.example.demo_web.dto;



import java.time.LocalDate;
import java.util.List;

public class HotelContractDto {
    private Long contractId;
    private String hotelName;
    private LocalDate startDate;
    private LocalDate endDate;
    private float markup;
    private List<RoomTypeDto> roomTypes;

    // Getters and Setters...


    public HotelContractDto( Long contractId, String hotelName, LocalDate startDate, LocalDate endDate, float markup, List<RoomTypeDto> roomTypes )
    {
        this.contractId = contractId;
        this.hotelName = hotelName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.markup = markup;
        this.roomTypes = roomTypes;
    }

    public HotelContractDto()
    {

    }

    public Long getContractId()
    {
        return contractId;
    }

    public void setContractId( Long contractId )
    {
        this.contractId = contractId;
    }

    public String getHotelName()
    {
        return hotelName;
    }

    public void setHotelName( String hotelName )
    {
        this.hotelName = hotelName;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate( LocalDate startDate )
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate( LocalDate endDate )
    {
        this.endDate = endDate;
    }

    public float getMarkup()
    {
        return markup;
    }

    public void setMarkup( float markup )
    {
        this.markup = markup;
    }

    public List<RoomTypeDto> getRoomTypes()
    {
        return roomTypes;
    }

    public void setRoomTypes( List<RoomTypeDto> roomTypes )
    {
        this.roomTypes = roomTypes;
    }



    public static class RoomTypeDto {
        private Long roomTypeId;
        private String roomTypeName;
        private float basePrice;
        private int numberOfRooms;
        private int maxAdults;

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

        // Getters and Setters...
    }
}

