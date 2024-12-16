package com.example.demo_web.controller;

import com.example.demo_web.dto.request_body.HotelSearchRequestDto;
import com.example.demo_web.dto.response_body.NewRoomTypeSearchResultDto;
import com.example.demo_web.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling room type operations.
 *
 * <p>This controller provides endpoints for searching available room types
 * based on search criteria provided by the user.</p>
 */
@RestController
@RequestMapping("/api/v1/contract")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    /**
     * Constructs a {@code RoomTypeController} with the specified {@link RoomTypeService}.
     *
     * @param roomTypeService the service to handle room type operations.
     */
    @Autowired
    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    /**
     * Searches for available room types based on the given search criteria.
     *
     * @param searchDto the search criteria provided in the request body, encapsulated
     *                  in a {@link HotelSearchRequestDto}.
     * @return a {@link ResponseEntity} containing a list of {@link NewRoomTypeSearchResultDto}
     *         objects representing the filtered room types, with a status of {@code HttpStatus.OK}.
     */
    @PostMapping("/searchNew")
    public ResponseEntity<List<NewRoomTypeSearchResultDto>> searchContractsNew(
            @RequestBody HotelSearchRequestDto searchDto) {

        List<NewRoomTypeSearchResultDto> filteredRoomTypes = roomTypeService.getAvailableRoomTypesNew(searchDto);

        return ResponseEntity.ok(filteredRoomTypes);
    }
}
