package com.example.demo_web.dto;

/**
 * Data Transfer Object (DTO) representing a Room Type.
 * This class is used to transfer data related to a room type, such as the room's ID, name, base price,
 * the number of rooms, and the maximum number of adults allowed for the room type.
 * It is typically used in the context of sending room type data between the client and server.
 *
 * @author [Your Name]
 * @version 1.0
 */
public class RoomTypeDto {

    /**
     * The unique identifier of the room type.
     */
    private Long roomTypeId;

    /**
     * The name of the room type.
     */
    private String roomTypeName;

    /**
     * The base price for the room type.
     */
    private float basePrice;

    /**
     * The number of available rooms for this room type.
     */
    private int numberOfRooms;

    /**
     * The maximum number of adults that can be accommodated in this room type.
     */
    private int maxAdults;

    /**
     * Gets the unique identifier of the room type.
     *
     * @return the room type ID.
     */
    public Long getRoomTypeId() {
        return roomTypeId;
    }

    /**
     * Sets the unique identifier of the room type.
     *
     * @param roomTypeId the room type ID to set.
     */
    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    /**
     * Gets the name of the room type.
     *
     * @return the room type name.
     */
    public String getRoomTypeName() {
        return roomTypeName;
    }

    /**
     * Sets the name of the room type.
     *
     * @param roomTypeName the room type name to set.
     */
    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    /**
     * Gets the base price for the room type.
     *
     * @return the base price.
     */
    public float getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the base price for the room type.
     *
     * @param basePrice the base price to set.
     */
    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Gets the number of available rooms for this room type.
     *
     * @return the number of rooms.
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Sets the number of available rooms for this room type.
     *
     * @param numberOfRooms the number of rooms to set.
     */
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Gets the maximum number of adults that can be accommodated in this room type.
     *
     * @return the maximum number of adults.
     */
    public int getMaxAdults() {
        return maxAdults;
    }

    /**
     * Sets the maximum number of adults that can be accommodated in this room type.
     *
     * @param maxAdults the maximum number of adults to set.
     */
    public void setMaxAdults(int maxAdults) {
        this.maxAdults = maxAdults;
    }

}
