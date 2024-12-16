package com.example.demo_web.dto.request_body;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO for creating or updating hotel contracts.
 *
 * <p>This class encapsulates the details required for a hotel contract, including
 * the hotel's name, the contract's validity period, a markup percentage, and
 * a list of room types.</p>
 */
public class HotelContractRequestDto {

    /**
     * The name of the hotel for which the contract is created.
     */
    private String hotelName;

    /**
     * The start date of the contract in ISO-8601 format (yyyy-MM-dd).
     */
    private LocalDate startDate;

    /**
     * The end date of the contract in ISO-8601 format (yyyy-MM-dd).
     */
    private LocalDate endDate;

    /**
     * The markup percentage applied to the base price of rooms.
     */
    private float markup;

    /**
     * The list of room types associated with the hotel contract.
     */
    private List<RoomTypeDto> roomTypes;

    /**
     * Constructs a {@code HotelContractRequestDto} with the specified details.
     *
     * @param hotelName the name of the hotel.
     * @param startDate the start date of the contract.
     * @param endDate the end date of the contract.
     * @param markup the markup percentage for the contract.
     * @param roomTypes the list of room types associated with the contract.
     */
    public HotelContractRequestDto(String hotelName, LocalDate startDate, LocalDate endDate, float markup, List<RoomTypeDto> roomTypes) {
        this.hotelName = hotelName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.markup = markup;
        this.roomTypes = roomTypes;
    }

    // Getters and Setters...

    /**
     * Nested DTO representing a room type associated with a hotel contract.
     */
    public static class RoomTypeDto {

        /**
         * The name of the room type (e.g., "Deluxe Room").
         */
        private String roomTypeName;

        /**
         * The base price of the room type.
         */
        private float basePrice;

        /**
         * The number of rooms available for this room type.
         */
        private int numberOfRooms;

        /**
         * The maximum number of adults allowed in this room type.
         */
        private int maxAdults;

        // Getters and Setters...

        /**
         * Retrieves the name of the room type.
         *
         * @return the room type name.
         */
        public String getRoomTypeName() {
            return roomTypeName;
        }

        /**
         * Sets the name of the room type.
         *
         * @param roomTypeName the name of the room type.
         */
        public void setRoomTypeName(String roomTypeName) {
            this.roomTypeName = roomTypeName;
        }

        /**
         * Retrieves the base price of the room type.
         *
         * @return the base price.
         */
        public float getBasePrice() {
            return basePrice;
        }

        /**
         * Sets the base price of the room type.
         *
         * @param basePrice the base price.
         */
        public void setBasePrice(float basePrice) {
            this.basePrice = basePrice;
        }

        /**
         * Retrieves the number of rooms available for this room type.
         *
         * @return the number of rooms.
         */
        public int getNumberOfRooms() {
            return numberOfRooms;
        }

        /**
         * Sets the number of rooms available for this room type.
         *
         * @param numberOfRooms the number of rooms.
         */
        public void setNumberOfRooms(int numberOfRooms) {
            this.numberOfRooms = numberOfRooms;
        }

        /**
         * Retrieves the maximum number of adults allowed for this room type.
         *
         * @return the maximum number of adults.
         */
        public int getMaxAdults() {
            return maxAdults;
        }

        /**
         * Sets the maximum number of adults allowed for this room type.
         *
         * @param maxAdults the maximum number of adults.
         */
        public void setMaxAdults(int maxAdults) {
            this.maxAdults = maxAdults;
        }
    }

    // Getters and setters for HotelContractRequestDto fields...

    /**
     * Retrieves the hotel name for the contract.
     *
     * @return the hotel name.
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Sets the hotel name for the contract.
     *
     * @param hotelName the name of the hotel.
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * Retrieves the start date of the contract.
     *
     * @return the start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the contract.
     *
     * @param startDate the start date of the contract.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieves the end date of the contract.
     *
     * @return the end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the contract.
     *
     * @param endDate the end date of the contract.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the markup percentage of the contract.
     *
     * @return the markup percentage.
     */
    public float getMarkup() {
        return markup;
    }

    /**
     * Sets the markup percentage of the contract.
     *
     * @param markup the markup percentage.
     */
    public void setMarkup(float markup) {
        this.markup = markup;
    }

    /**
     * Retrieves the list of room types associated with the contract.
     *
     * @return the list of room types.
     */
    public List<RoomTypeDto> getRoomTypes() {
        return roomTypes;
    }

    /**
     * Sets the list of room types associated with the contract.
     *
     * @param roomTypes the list of room types.
     */
    public void setRoomTypes(List<RoomTypeDto> roomTypes) {
        this.roomTypes = roomTypes;
    }
}
