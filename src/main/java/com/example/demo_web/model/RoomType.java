package com.example.demo_web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents a room type associated with a hotel contract.
 * This entity defines the attributes for a room type, including its price, availability, and maximum capacity.
 */
@Entity
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomtypeId;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private HotelContract hotelContract;

    private String roomtypeName;
    private float baseprice;
    private int numberofRooms;
    private int maxAdults;

    /**
     * Default constructor for RoomType.
     * Initializes a new instance of RoomType without setting any attributes.
     */
    public RoomType() {
    }

    /**
     * Constructs a new RoomType with the specified attributes.
     *
     * @param roomtypeId    the ID of the room type
     * @param hotelContract the hotel contract associated with the room type
     * @param roomtypeName  the name of the room type
     * @param baseprice     the base price for the room type
     * @param numberofRooms the number of rooms available for this room type
     * @param maxAdults     the maximum number of adults that can stay in the room type
     */
    public RoomType(long roomtypeId, HotelContract hotelContract, String roomtypeName, float baseprice, int numberofRooms, int maxAdults) {
        this.roomtypeId = roomtypeId;
        this.hotelContract = hotelContract;
        this.roomtypeName = roomtypeName;
        this.baseprice = baseprice;
        this.numberofRooms = numberofRooms;
        this.maxAdults = maxAdults;
    }

    /**
     * Gets the ID of the room type.
     *
     * @return the room type ID
     */
    public long getRoomtypeId() {
        return roomtypeId;
    }

    /**
     * Sets the ID of the room type.
     *
     * @param roomtypeId the room type ID to set
     */
    public void setRoomtypeId(long roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    /**
     * Gets the hotel contract associated with the room type.
     *
     * @return the hotel contract
     */
    public HotelContract getHotelContract() {
        return hotelContract;
    }

    /**
     * Sets the hotel contract associated with the room type.
     *
     * @param hotelContract the hotel contract to set
     */
    public void setHotelContract(HotelContract hotelContract) {
        this.hotelContract = hotelContract;
    }

    /**
     * Gets the name of the room type.
     *
     * @return the room type name
     */
    public String getRoomtypeName() {
        return roomtypeName;
    }

    /**
     * Sets the name of the room type.
     *
     * @param roomtypeName the room type name to set
     */
    public void setRoomtypeName(String roomtypeName) {
        this.roomtypeName = roomtypeName;
    }

    /**
     * Gets the base price of the room type.
     *
     * @return the base price
     */
    public float getBaseprice() {
        return baseprice;
    }

    /**
     * Sets the base price of the room type.
     *
     * @param baseprice the base price to set
     */
    public void setBaseprice(float baseprice) {
        this.baseprice = baseprice;
    }

    /**
     * Gets the number of rooms available for this room type.
     *
     * @return the number of rooms
     */
    public int getNumberofRooms() {
        return numberofRooms;
    }

    /**
     * Sets the number of rooms available for this room type.
     *
     * @param numberofRooms the number of rooms to set
     */
    public void setNumberofRooms(int numberofRooms) {
        this.numberofRooms = numberofRooms;
    }

    /**
     * Gets the maximum number of adults allowed in the room type.
     *
     * @return the maximum number of adults
     */
    public int getMaxAdults() {
        return maxAdults;
    }

    /**
     * Sets the maximum number of adults allowed in the room type.
     *
     * @param maxAdults the maximum number of adults to set
     */
    public void setMaxAdults(int maxAdults) {
        this.maxAdults = maxAdults;
    }
}
