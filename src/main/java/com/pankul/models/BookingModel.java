package com.pankul.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a hotel booking entity mapped to the "bookings" table in the
 * database.
 * <p>
 * This model contains booking details such as the associated hotel and user,
 * check-in and check-out dates, number of guests, total price, and booking
 * status.
 * </p>
 *
 * <p>
 * Example usage:
 * 
 * <pre>
 * BookingModel booking = new BookingModel();
 * booking.setHotelId(1L);
 * booking.setCheckInDate(LocalDate.parse("01-08-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
 * booking.setStatus("CONFIRMED");
 * </pre>
 * </p>
 *
 * @author pankulbindal
 */
@Entity
@Table(name = "bookings")
public class BookingModel {

	/**
	 * The unique identifier for this booking.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The ID of the associated hotel.
	 */
	@Column(name = "hotel_id")
	private Long hotelId;

	/**
	 * The user associated with this booking.
	 * <p>
	 * Fetched eagerly and ignored in JSON serialization.
	 * </p>
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uid", referencedColumnName = "id")
	private UsersModel user;

	/**
	 * The check-in date for the booking (format: dd-MM-yyyy).
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "check_in_date")
	private LocalDate checkInDate;

	/**
	 * The check-out date for the booking (format: dd-MM-yyyy).
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "check_out_date")
	private LocalDate checkOutDate;

	/**
	 * The number of guests included in the booking.
	 */
	@Column(name = "num_guests")
	private Integer numGuests;

	/**
	 * The total price for the booking.
	 */
	@Column(name = "total_price")
	private Double totalPrice;

	/**
	 * The booking status (e.g., "CONFIRMED", "CANCELLED", "PENDING").
	 */
	@Column(name = "status")
	private String status;

	// Getters and setters

	/**
	 * Gets the unique booking ID.
	 * 
	 * @return the booking ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique booking ID.
	 * 
	 * @param id the booking ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the associated hotel ID.
	 * 
	 * @return the hotel ID
	 */
	public Long getHotelId() {
		return hotelId;
	}

	/**
	 * Sets the associated hotel ID.
	 * 
	 * @param hotelId the hotel ID
	 */
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	/**
	 * Gets the user associated with the booking.
	 * 
	 * @return the user
	 */
	public UsersModel getUser() {
		return user;
	}

	/**
	 * Sets the user associated with the booking.
	 * 
	 * @param user the user
	 */
	public void setUser(UsersModel user) {
		this.user = user;
	}

	/**
	 * Gets the check-in date of the booking.
	 * 
	 * @return the check-in date
	 */
	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	/**
	 * Sets the check-in date of the booking.
	 * 
	 * @param checkInDate the check-in date
	 */
	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	/**
	 * Gets the check-out date of the booking.
	 * 
	 * @return the check-out date
	 */
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * Sets the check-out date of the booking.
	 * 
	 * @param checkOutDate the check-out date
	 */
	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	/**
	 * Gets the number of guests for the booking.
	 * 
	 * @return the number of guests
	 */
	public Integer getNumGuests() {
		return numGuests;
	}

	/**
	 * Sets the number of guests for the booking.
	 * 
	 * @param numGuests the number of guests
	 */
	public void setNumGuests(Integer numGuests) {
		this.numGuests = numGuests;
	}

	/**
	 * Gets the total price for the booking.
	 * 
	 * @return the total price
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Sets the total price for the booking.
	 * 
	 * @param totalPrice the total price
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Gets the current booking status.
	 * 
	 * @return the booking status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the booking status.
	 * 
	 * @param status the booking status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}