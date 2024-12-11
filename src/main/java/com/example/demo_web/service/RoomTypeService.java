package com.example.demo_web.service;

import com.example.demo_web.dto.HotelContractDto;
import com.example.demo_web.dto.RoomTypeDto;
import com.example.demo_web.dto.request_body.HotelSearchRequestDto;
import com.example.demo_web.dto.response_body.RoomTypeSearchResultDto;
import com.example.demo_web.model.HotelContract;
import com.example.demo_web.model.RoomType;
import com.example.demo_web.repository.RoomTypeRepository;
import com.example.demo_web.utils.EntityToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomTypeService
{
    private final RoomTypeRepository roomRepository;

    @Autowired
    public RoomTypeService( RoomTypeRepository roomRepository )
    {
        this.roomRepository = roomRepository;
    }


    public List<RoomTypeSearchResultDto> getAvailableRoomTypes( HotelSearchRequestDto searchDto) {

        LocalDate checkInDate = searchDto.getCheckInDate();
        LocalDate checkOutDate = checkInDate.plusDays( searchDto.getNumberOfNights() );
        int maxAdultsInSelection = searchDto.getSelections().stream().mapToInt(HotelSearchRequestDto.Selection::getNumberOfAdults).max().orElse(0);
        int totalRoomsRequested = searchDto.getSelections().stream().mapToInt(HotelSearchRequestDto.Selection::getNumberOfRooms).sum();

        //Fetch all rooms
        List<RoomType> allRoomTypes = roomRepository.findAll();

        // Filter room types based on availability of contracts within given dates.
        List<RoomType> filteredByDate = filterRoomTypesByDateRange( allRoomTypes, checkInDate, checkOutDate );

        //Filter room types based on max adults capacity
        List<RoomType> filteredByAdults = filterRoomTypesByMaxAdults( filteredByDate, maxAdultsInSelection );

        //Filter based on total rooms requested
        List<RoomType> finalFilteredRooms = filterRoomTypesByTotalRooms( filteredByAdults,totalRoomsRequested );

        return finalFilteredRooms.stream().map(EntityToDtoConverter::RoomTypeEntityToRoomTypeSearchResultDto).collect( Collectors.toList() );
    }



    /**
     * Filter room types by checking if contracts exist within the specified date range.
     */
    private List<RoomType> filterRoomTypesByDateRange(List<RoomType> roomTypes, LocalDate checkInDate, LocalDate checkOutDate) {
        return roomTypes.stream()
                        .filter(roomType -> {
                            HotelContract contract = roomType.getHotelContract();
                            return isDateInRange(checkInDate, checkOutDate, contract.getStartDate(), contract.getEndDate());
                        })
                        .collect(Collectors.toList());
    }

    /**
     * Check if the given date range (check-in to check-out) falls within a contract's start and end date.
     */
    private boolean isDateInRange( LocalDate checkInDate, LocalDate checkOutDate, LocalDate contractStartDate, LocalDate contractEndDate) {
        return (checkInDate.isAfter(contractStartDate) || checkInDate.isEqual(contractStartDate)) &&
                       (checkOutDate.isBefore(contractEndDate) || checkOutDate.isEqual(contractEndDate));
    }

    /**
     * Filter room types where max adults capacity is greater than or equal to the max adults in the selection.
     */
    private List<RoomType> filterRoomTypesByMaxAdults(List<RoomType> roomTypes, int maxAdultsInSelection) {
        return roomTypes.stream()
                        .filter(roomType -> roomType.getMaxAdults() >= maxAdultsInSelection)
                        .collect(Collectors.toList());
    }

    /**
     * Filter room types based on the total number of rooms requested.
     */
    private List<RoomType> filterRoomTypesByTotalRooms(List<RoomType> roomTypes, int totalRoomsRequested) {
        return roomTypes.stream()
                        .filter(roomType -> roomType.getNumberofRooms() >= totalRoomsRequested)
                        .collect(Collectors.toList());
    }


}
