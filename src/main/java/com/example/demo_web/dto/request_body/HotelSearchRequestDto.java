package com.example.demo_web.dto.request_body;

import java.time.LocalDate;
import java.util.List;

public class HotelSearchRequestDto
{
    private LocalDate checkInDate;
    private int numberOfNights;
    private List<Selection> selections;

    public HotelSearchRequestDto( LocalDate checkInDate, int numberOfNights, List<Selection> selections )
    {
        this.checkInDate = checkInDate;
        this.numberOfNights = numberOfNights;
        this.selections = selections;
    }

    public LocalDate getCheckInDate()
    {
        return checkInDate;
    }

    public void setCheckInDate( LocalDate checkInDate )
    {
        this.checkInDate = checkInDate;
    }

    public int getNumberOfNights()
    {
        return numberOfNights;
    }

    public void setNumberOfNights( int numberOfNights )
    {
        this.numberOfNights = numberOfNights;
    }

    public List<Selection> getSelections()
    {
        return selections;
    }

    public void setSelections( List<Selection> selections )
    {
        this.selections = selections;
    }




    public static class Selection{
        private int numberOfRooms;
        private int numberOfAdults;

        public int getNumberOfRooms()
        {
            return numberOfRooms;
        }

        public void setNumberOfRooms( int numberOfRooms )
        {
            this.numberOfRooms = numberOfRooms;
        }

        public int getNumberOfAdults()
        {
            return numberOfAdults;
        }

        public void setNumberOfAdults( int numberOfAdults )
        {
            this.numberOfAdults = numberOfAdults;
        }

        public Selection( int numberOfRooms, int numberOfAdults )
        {
            this.numberOfRooms = numberOfRooms;
            this.numberOfAdults = numberOfAdults;
        }
    }



}
