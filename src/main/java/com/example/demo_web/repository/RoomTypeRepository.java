package com.example.demo_web.repository;

import com.example.demo_web.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for accessing and managing {@link RoomType} entities.
 * This interface extends {@link JpaRepository} to provide standard CRUD operations for {@link RoomType}.
 * It includes custom query methods to find available room types based on various filtering criteria.
 *
 * @author [Your Name]
 * @version 1.0
 */
@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

    /**
     * Finds available room types based on the provided check-in and check-out dates,
     * the maximum number of adults, and the total number of rooms requested.
     * This method uses a custom JPQL query to filter room types based on the availability of hotel contracts,
     * the number of adults the room can accommodate, and the number of rooms available.
     *
     * @param checkInDate the check-in date to filter by.
     * @param checkOutDate the check-out date to filter by.
     * @param maxAdults the maximum number of adults the room can accommodate.
     * @param totalRoomsRequested the total number of rooms requested.
     * @return a list of available {@link RoomType} objects that match the filtering criteria.
     */
    @Query("SELECT r FROM RoomType r JOIN r.hotelContract c WHERE " +
                   "(c.startDate <= :checkOutDate AND c.endDate >= :checkInDate) AND " +  // Date range filter
                   "(r.maxAdults >= :maxAdults) AND " +  // Max adults filter
                   "(r.numberofRooms >= :totalRoomsRequested)")  // Total rooms requested filter
    List<RoomType> findAvailableRoomTypes(LocalDate checkInDate, LocalDate checkOutDate, int maxAdults, int totalRoomsRequested);

    /**
     * Finds room types based on the provided check-in and check-out dates.
     * This method uses a custom JPQL query to filter room types by the availability of hotel contracts within the specified date range.
     *
     * @param checkInDate the check-in date to filter by.
     * @param checkOutDate the check-out date to filter by.
     * @return a list of {@link RoomType} objects that are available within the specified date range.
     */
    @Query("SELECT r FROM RoomType r JOIN r.hotelContract c WHERE " +
                   "(c.startDate <= :checkOutDate AND c.endDate >= :checkInDate)")  // Date range filter only
    List<RoomType> findRoomTypesByDateRange(LocalDate checkInDate, LocalDate checkOutDate);
}
