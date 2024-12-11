package com.example.demo_web.repository;

import com.example.demo_web.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

    // Custom query to filter RoomTypes based on dates, max adults, and total rooms requested
    @Query("SELECT r FROM RoomType r JOIN r.hotelContract c WHERE " +
                   "(c.startDate <= :checkOutDate AND c.endDate >= :checkInDate) AND " +  // Date range filter
                   "(r.maxAdults >= :maxAdults) AND " +  // Max adults filter
                   "(r.numberofRooms >= :totalRoomsRequested)")  // Total rooms requested filter
    List<RoomType> findAvailableRoomTypes(LocalDate checkInDate, LocalDate checkOutDate, int maxAdults, int totalRoomsRequested);

    // Custom query to filter RoomTypes based only on dates
    @Query("SELECT r FROM RoomType r JOIN r.hotelContract c WHERE " +
                   "(c.startDate <= :checkOutDate AND c.endDate >= :checkInDate)")  // Date range filter only
    List<RoomType> findRoomTypesByDateRange(LocalDate checkInDate, LocalDate checkOutDate);
}
