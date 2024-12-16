package com.example.demo_web.dto.request_body;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO for searching hotel contracts based on check-in date, duration, and room selections.
 *
 * <p>This class encapsulates the details required for searching available hotel options,
 * including the desired check-in date, number of nights to stay, and specific room
 * selections for adults and rooms.</p>
 */
public class HotelSearchRequestDto {

    /**
     * The check-in date for the hotel search in ISO-8601 format (yyyy-MM-dd).
     */
    private LocalDate checkInDate;

    /**
     * The number of nights for the stay.
     */
    private int numberOfNights;

    /**
     * A list of room and adult selections for the search criteria.
     */
    private List<Selection> selections;

    /**
     * Constructs a {@code HotelSearchRequestDto} with the specified details.
     *
     * @param checkInDate the desired check-in date.
     * @param numberOfNights the number of nights for the stay.
     * @param selections the list of room and adult selections.
     */
    public HotelSearchRequestDto(LocalDate checkInDate, int numberOfNights, List<Selection> selections) {
        this.checkInDate = checkInDate;
        this.numberOfNights = numberOfNights;
        this.selections = selections;
    }

    /**
     * Retrieves the check-in date for the hotel search.
     *
     * @return the check-in date.
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    /**
     * Sets the check-in date for the hotel search.
     *
     * @param checkInDate the desired check-in date.
     */
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Retrieves the number of nights for the stay.
     *
     * @return the number of nights.
     */
    public int getNumberOfNights() {
        return numberOfNights;
    }

    /**
     * Sets the number of nights for the stay.
     *
     * @param numberOfNights the number of nights.
     */
    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    /**
     * Retrieves the list of room and adult selections.
     *
     * @return the list of selections.
     */
    public List<Selection> getSelections() {
        return selections;
    }

    /**
     * Sets the list of room and adult selections.
     *
     * @param selections the list of selections.
     */
    public void setSelections(List<Selection> selections) {
        this.selections = selections;
    }

    /**
     * Nested DTO representing a room and adult selection in the search criteria.
     */
    public static class Selection {

        /**
         * The number of rooms requested in this selection.
         */
        private int numberOfRooms;

        /**
         * The number of adults requested in this selection.
         */
        private int numberOfAdults;

        /**
         * Constructs a {@code Selection} with the specified room and adult counts.
         *
         * @param numberOfRooms the number of rooms.
         * @param numberOfAdults the number of adults.
         */
        public Selection(int numberOfRooms, int numberOfAdults) {
            this.numberOfRooms = numberOfRooms;
            this.numberOfAdults = numberOfAdults;
        }

        /**
         * Retrieves the number of rooms in this selection.
         *
         * @return the number of rooms.
         */
        public int getNumberOfRooms() {
            return numberOfRooms;
        }

        /**
         * Sets the number of rooms in this selection.
         *
         * @param numberOfRooms the number of rooms.
         */
        public void setNumberOfRooms(int numberOfRooms) {
            this.numberOfRooms = numberOfRooms;
        }

        /**
         * Retrieves the number of adults in this selection.
         *
         * @return the number of adults.
         */
        public int getNumberOfAdults() {
            return numberOfAdults;
        }

        /**
         * Sets the number of adults in this selection.
         *
         * @param numberOfAdults the number of adults.
         */
        public void setNumberOfAdults(int numberOfAdults) {
            this.numberOfAdults = numberOfAdults;
        }
    }
}
