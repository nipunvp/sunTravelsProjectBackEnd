package com.example.demo_web.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents a hotel contract entity in the system.
 *
 * <p>This class encapsulates the details of a hotel contract, including the hotel name,
 * the contract start and end dates, the markup applied, and the associated room types
 * for the contract.</p>
 */
@Entity
public class HotelContract {

    /**
     * The unique identifier for the hotel contract.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    /**
     * The name of the hotel associated with this contract.
     */
    private String hotelName;

    /**
     * The start date of the contract.
     */
    private LocalDate startDate;

    /**
     * The end date of the contract.
     */
    private LocalDate endDate;

    /**
     * The markup percentage applied to the contract.
     */
    private float markup;

    /**
     * The list of room types associated with this contract.
     * This is a one-to-many relationship with the {@link RoomType} entity.
     */
    @OneToMany(mappedBy = "hotelContract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomType> roomTypes;

    /**
     * Retrieves the contract ID.
     *
     * @return the contract ID.
     */
    public Long getContractId() {
        return contractId;
    }

    /**
     * Sets the contract ID.
     *
     * @param contractId the contract ID to set.
     */
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    /**
     * Retrieves the hotel name.
     *
     * @return the hotel name.
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Sets the hotel name.
     *
     * @param hotelName the hotel name to set.
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * Retrieves the start date of the contract.
     *
     * @return the start date of the contract.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the contract.
     *
     * @param startDate the start date to set.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieves the end date of the contract.
     *
     * @return the end date of the contract.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the contract.
     *
     * @param endDate the end date to set.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the markup percentage applied to the contract.
     *
     * @return the markup percentage.
     */
    public float getMarkup() {
        return markup;
    }

    /**
     * Sets the markup percentage for the contract.
     *
     * @param markup the markup percentage to set.
     */
    public void setMarkup(float markup) {
        this.markup = markup;
    }

    /**
     * Retrieves the list of room types associated with the contract.
     *
     * @return the list of room types.
     */
    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    /**
     * Sets the list of room types for the contract.
     *
     * @param roomTypes the list of room types to set.
     */
    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    /**
     * Default constructor for {@code HotelContract}.
     */
    public HotelContract() {
    }

    /**
     * Constructs a {@code HotelContract} with the specified details.
     *
     * @param contractId the contract ID.
     * @param hotelName the name of the hotel.
     * @param startDate the start date of the contract.
     * @param endDate the end date of the contract.
     * @param markup the markup percentage applied to the contract.
     * @param roomTypes the list of room types associated with the contract.
     */
    public HotelContract(Long contractId, String hotelName, LocalDate startDate, LocalDate endDate, float markup, List<RoomType> roomTypes) {
        this.contractId = contractId;
        this.hotelName = hotelName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.markup = markup;
        this.roomTypes = roomTypes;
    }
}
