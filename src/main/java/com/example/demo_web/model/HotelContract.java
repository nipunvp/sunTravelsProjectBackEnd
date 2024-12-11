package com.example.demo_web.model;

import com.example.demo_web.dto.RoomTypeDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
public class HotelContract
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;
    private String hotelName;
    private LocalDate startDate;
    private LocalDate endDate;
    private float markup;

    @OneToMany(mappedBy = "hotelContract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomType> roomTypes;


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

    public List<RoomType> getRoomTypes()
    {
        return roomTypes;
    }

    public void setRoomTypes( List<RoomType> roomTypes )
    {
        this.roomTypes = roomTypes;
    }

    public HotelContract()
    {
    }

    public HotelContract( Long contractId, String hotelName, LocalDate startDate, LocalDate endDate, float markup, List<RoomType> roomTypes )
    {
        this.contractId = contractId;
        this.hotelName = hotelName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.markup = markup;
        this.roomTypes = roomTypes;
    }




}
