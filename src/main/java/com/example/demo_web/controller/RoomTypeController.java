package com.example.demo_web.controller;

import com.example.demo_web.dto.request_body.HotelSearchRequestDto;
import com.example.demo_web.dto.response_body.NewRoomTypeSearchResultDto;
import com.example.demo_web.dto.response_body.RoomTypeSearchResultDto;
import com.example.demo_web.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contract")
public class RoomTypeController
{
    private final RoomTypeService roomTypeService;

    @Autowired
    public RoomTypeController( RoomTypeService roomTypeService )
    {
        this.roomTypeService = roomTypeService;
    }


    @PostMapping("/search")
    public ResponseEntity<List<RoomTypeSearchResultDto>> searchContracts( @RequestBody HotelSearchRequestDto searchDto ){

        List<RoomTypeSearchResultDto> filteredRoomTypes = roomTypeService.getAvailableRoomTypes(searchDto);

        return ResponseEntity.ok(filteredRoomTypes);
    }


    @PostMapping("/searchNew")
    public ResponseEntity<List<NewRoomTypeSearchResultDto>> searchContractsNew( @RequestBody HotelSearchRequestDto searchDto ){

        List<NewRoomTypeSearchResultDto> filteredRoomTypes = roomTypeService.getAvailableRoomTypesNew(searchDto);

        return ResponseEntity.ok(filteredRoomTypes);
    }

}
