package com.example.demo_web.service;

import com.example.demo_web.dto.request_body.HotelSearchRequestDto;
import com.example.demo_web.dto.response_body.NewRoomTypeSearchResultDto;
import com.example.demo_web.dto.response_body.RoomTypeSearchResultDto;
import com.example.demo_web.exception.InvalidSearchException;
import com.example.demo_web.model.HotelContract;
import com.example.demo_web.model.RoomType;
import com.example.demo_web.repository.RoomTypeRepository;
import com.example.demo_web.utils.EntityToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class responsible for handling room type-related operations such as filtering room types
 * based on the search criteria, validating search requests, and converting room type data into DTOs.
 */
@Service
public class RoomTypeService {

    private final RoomTypeRepository roomRepository;

    /**
     * Constructor for RoomTypeService.
     *
     * @param roomRepository the repository for RoomType entities
     */
    @Autowired
    public RoomTypeService(RoomTypeRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * This method was the old method used for fetching available room types, which is no longer in use.
     * It validated and filtered room types based on the search criteria.
     *
     * @param searchDto the search criteria including check-in date, number of nights, and room selections
     * @return a list of RoomTypeSearchResultDto objects representing available room types
     */
    public List<RoomTypeSearchResultDto> getAvailableRoomTypes(HotelSearchRequestDto searchDto) {
        //validateSearchRequest(searchDto);

        LocalDate checkInDate = searchDto.getCheckInDate();
        LocalDate checkOutDate = checkInDate.plusDays(searchDto.getNumberOfNights());
        int maxAdultsInSelection = searchDto.getSelections().stream().mapToInt(HotelSearchRequestDto.Selection::getNumberOfAdults).max().orElse(0);
        int totalRoomsRequested = searchDto.getSelections().stream().mapToInt(HotelSearchRequestDto.Selection::getNumberOfRooms).sum();

        // Use custom query to fetch filtered room types directly from the database
        List<RoomType> filteredRooms = roomRepository.findAvailableRoomTypes(checkInDate, checkOutDate, maxAdultsInSelection, totalRoomsRequested);

        return filteredRooms.stream()
                            .map(EntityToDtoConverter::RoomTypeEntityToRoomTypeSearchResultDto)
                            .collect(Collectors.toList());
    }

    /**
     * Filters the given list of room types based on whether their associated contracts
     * fall within the specified date range.
     *
     * @param roomTypes     the list of RoomType entities to filter
     * @param checkInDate   the check-in date for the search
     * @param checkOutDate  the check-out date for the search
     * @return a filtered list of RoomType entities that meet the date range criteria
     */
    public List<RoomType> filterRoomTypesByDateRange(List<RoomType> roomTypes, LocalDate checkInDate, LocalDate checkOutDate) {
        return roomTypes.stream()
                        .filter(roomType -> {
                            HotelContract contract = roomType.getHotelContract();
                            return isDateInRange(checkInDate, checkOutDate, contract.getStartDate(), contract.getEndDate());
                        })
                        .collect(Collectors.toList());
    }

    /**
     * Checks if the provided date range (check-in to check-out) falls within the contract's start and end date.
     *
     * @param checkInDate    the check-in date
     * @param checkOutDate   the check-out date
     * @param contractStartDate the contract's start date
     * @param contractEndDate   the contract's end date
     * @return true if the date range is within the contract's start and end dates, false otherwise
     */
    private boolean isDateInRange(LocalDate checkInDate, LocalDate checkOutDate, LocalDate contractStartDate, LocalDate contractEndDate) {
        return (checkInDate.isAfter(contractStartDate) || checkInDate.isEqual(contractStartDate)) &&
                       (checkOutDate.isBefore(contractEndDate) || checkOutDate.isEqual(contractEndDate));
    }

    /**
     * Filters the given list of room types by checking if the maximum adult capacity is greater than or equal
     * to the maximum adults specified in the search selection.
     *
     * @param roomTypes           the list of RoomType entities to filter
     * @param maxAdultsInSelection the maximum number of adults in the search selection
     * @return a filtered list of RoomType entities that can accommodate the specified maximum adults
     */
    public List<RoomType> filterRoomTypesByMaxAdults(List<RoomType> roomTypes, int maxAdultsInSelection) {
        return roomTypes.stream()
                        .filter(roomType -> roomType.getMaxAdults() >= maxAdultsInSelection)
                        .collect(Collectors.toList());
    }

    /**
     * Filters the given list of room types based on whether the number of available rooms is greater than or equal
     * to the total number of rooms requested in the search selection.
     *
     * @param roomTypes           the list of RoomType entities to filter
     * @param totalRoomsRequested the total number of rooms requested in the search selection
     * @return a filtered list of RoomType entities that meet the total room request criteria
     */
    public List<RoomType> filterRoomTypesByTotalRooms(List<RoomType> roomTypes, int totalRoomsRequested) {
        return roomTypes.stream()
                        .filter(roomType -> roomType.getNumberofRooms() >= totalRoomsRequested)
                        .collect(Collectors.toList());
    }

    /**
     * Validates the search request to ensure that all required fields are properly filled
     * and that the values are within acceptable ranges.
     *
     * @param searchDto the search request DTO to validate
     * @throws InvalidSearchException if any validation checks fail
     */
    private void validateSearchRequest(HotelSearchRequestDto searchDto) {
        LocalDate checkInDate = searchDto.getCheckInDate();
        int numberOfNights = searchDto.getNumberOfNights();
        List<HotelSearchRequestDto.Selection> selections = searchDto.getSelections();

        // Validation 1: Check-in date should be today or a date after today
        if (checkInDate == null || checkInDate.isBefore(LocalDate.now())) {
            throw new InvalidSearchException("Check-in date must be today or a future date.");
        }

        // Validation 2: Number of nights should be a positive integer
        if (numberOfNights <= 0) {
            throw new InvalidSearchException("Number of nights must be a positive integer.");
        }

        // Validation 3: There should be at least one selection
        if (selections == null || selections.isEmpty()) {
            throw new InvalidSearchException("At least one selection must be provided.");
        }

        // Validation 4: In every selection, number of rooms and number of adults should be positive integers
        for (HotelSearchRequestDto.Selection selection : selections) {
            if (selection.getNumberOfRooms() <= 0) {
                throw new InvalidSearchException("Number of rooms in a selection must be a positive integer.");
            }
            if (selection.getNumberOfAdults() <= 0) {
                throw new InvalidSearchException("Number of adults in a selection must be a positive integer.");
            }
        }
    }

    /**
     * Fetches available room types based on the provided search criteria, and returns them as a list of DTOs.
     *
     * @param searchDto the search request DTO containing the criteria
     * @return a list of NewRoomTypeSearchResultDto objects representing available room types
     */
    public List<NewRoomTypeSearchResultDto> getAvailableRoomTypesNew(HotelSearchRequestDto searchDto) {
        validateSearchRequest(searchDto);

        // Step 1: Validate and calculate the date range
        LocalDate checkInDate = searchDto.getCheckInDate();
        LocalDate checkOutDate = checkInDate.plusDays(searchDto.getNumberOfNights());

        // Step 2: Fetch room types filtered by date range
        List<RoomType> filteredRooms = fetchRoomTypesByDateRange(checkInDate, checkOutDate);

        if (filteredRooms.isEmpty()) {
            return Collections.emptyList(); // Return empty if no rooms are available
        }

        // Step 3: Group room types by hotel name
        Map<String, List<RoomType>> roomsByHotel = groupRoomsByHotel(filteredRooms);

        // Step 4: Find matching rooms based on search criteria
        Map<String, List<RoomType>> matchingRooms = findMatchingRooms(roomsByHotel, searchDto.getSelections());

        // Step 5: Convert matching rooms to DTOs and return
        return EntityToDtoConverter.HashMapToNewRoomTypeSearchResultDtoList(matchingRooms);
    }

    // Step 2: Fetch rooms filtered by date range
    private List<RoomType> fetchRoomTypesByDateRange(LocalDate checkInDate, LocalDate checkOutDate) {
        return roomRepository.findRoomTypesByDateRange(checkInDate, checkOutDate);
    }


    // Step 3: Group rooms by hotel name
    public Map<String, List<RoomType>> groupRoomsByHotel(List<RoomType> rooms) {
        Map<String, List<RoomType>> roomsByHotel = new HashMap<>();
        for (RoomType room : rooms) {
            String hotelName = room.getHotelContract().getHotelName();
            roomsByHotel.computeIfAbsent(hotelName, k -> new ArrayList<>()).add(room);
        }
        return roomsByHotel;
    }


    // Step 4: Find hotels that satisfy all selection criteria
    private Map<String, List<RoomType>> findMatchingRooms(Map<String, List<RoomType>> roomsByHotel, List<HotelSearchRequestDto.Selection> selections) {
        // This map will hold the hotels that satisfy all selection criteria, along with their valid room types
        Map<String, List<RoomType>> finalSelectedRooms = new HashMap<>();

        // Iterate over each hotel group in the roomsByHotel map
        for (Map.Entry<String, List<RoomType>> entry : roomsByHotel.entrySet()) {
            String hotelName = entry.getKey();
            List<RoomType> hotelRooms = entry.getValue();

            // List to hold the room types that match the selection criteria for the current hotel
            List<RoomType> selectedRoomsForHotel = new ArrayList<>();

            // Iterate through each selection object
            boolean allSelectionsSatisfied = true; // Flag to track if all selection criteria are satisfied for this hotel

            for (HotelSearchRequestDto.Selection selection : selections) {
                int numberOfRooms = selection.getNumberOfRooms();
                int maxAdults = selection.getNumberOfAdults();

                // Temporary list to hold rooms that match the current selection
                List<RoomType> matchingRoomsForSelection = new ArrayList<>();

                // Iterate through the room types in the hotel and check if they match the selection criteria
                for (RoomType room : hotelRooms) {
                    if (room.getMaxAdults() == maxAdults && room.getNumberofRooms() >= numberOfRooms) {
                        matchingRoomsForSelection.add(room);
                    }
                }

                // If no matching rooms were found for this selection, set allSelectionsSatisfied to false
                if (matchingRoomsForSelection.isEmpty()) {
                    allSelectionsSatisfied = false;
                    break;
                }

                // Add the matching rooms for this selection to the selectedRoomsForHotel list
                selectedRoomsForHotel.addAll(matchingRoomsForSelection);
            }

            // If the hotel satisfies all selection criteria, add it to the finalSelectedRooms map
            if (allSelectionsSatisfied) {
                finalSelectedRooms.put(hotelName, selectedRoomsForHotel);
            }
        }

        return finalSelectedRooms;
    }
}
