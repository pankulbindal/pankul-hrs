package com.pankul.service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankul.models.BookingModel;
import com.pankul.models.HotelsModel;
import com.pankul.models.UsersModel;
import com.pankul.repo.BookingsRepo;
import com.pankul.repo.HotelsRepo;
import com.pankul.repo.UsersRepo;
import com.pankul.response.BookingResponse;
import com.pankul.response.StatusDescription;

@Service
public class BookingServiceImpl {

	@Autowired
	BookingsRepo bookingRepo;

	@Autowired
	HotelsRepo hotelsRepo;

	@Autowired
	UsersRepo userRepo;

	private static Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	public BookingResponse createBooking(BookingModel bookingRequest, String requestId) {
		BookingResponse response = new BookingResponse();
		StatusDescription status = new StatusDescription();
		response.setStatus(status);
		try {
			Optional<HotelsModel> hotel = hotelsRepo.findById(bookingRequest.getHotelId());
			if (hotel.isPresent()) {
				logger.info(requestId + "-- Create Booking Request - Hotel Found!");
				HotelsModel hotelDetails = hotel.get();
				Optional<UsersModel> user = userRepo.findById(bookingRequest.getId());
				bookingRequest.setId(null);
				if (user.isPresent()) {
					logger.info(requestId + "-- Create Booking Request - User Found!");
					UsersModel userDetails = user.get();
					int days = (int) ChronoUnit.DAYS.between(bookingRequest.getCheckInDate(),
							bookingRequest.getCheckOutDate());
					if (days >= 0) {
						if (days == 0) {
							days++;
						}
						double bookingPrice = days * hotelDetails.getHotelPrice();
						logger.info(requestId + "-- Create Booking Request - Booking for " + days
								+ " days with total price of " + bookingPrice);
						bookingRequest.setTotalPrice(bookingPrice);
						bookingRequest.setStatus("CONFIRMED");
						bookingRequest.setUser(userDetails);
						bookingRepo.save(bookingRequest);
						status.setStatus(200);
						status.setDescription("Booking Created!");
						response.setBooking(bookingRequest);
						return response;
					} else {
						status.setStatus(201);
						status.setDescription("Check out date should be after Check in date!");
						logger.info(requestId + "-- Create Booking Request - Check out date should be after Check in date!");
					}
				} else {
					status.setStatus(201);
					status.setDescription("User Id incorrect!");
					logger.info(requestId + "-- Create Booking Request - User Id is Incorrect!");
				}
			} else {
				status.setStatus(201);
				status.setDescription("Hotel Id Incorrect!");
				logger.info(requestId + "-- Create Booking Request - Hotel Id is Incorrect!");
			}
		} catch (Exception e) {
			status.setStatus(500);
			status.setDescription("Server Error!");
			logger.error(requestId + "-- Create Booking Request - " + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	public BookingResponse cancelBooking(Long bookingId, String requestId) {
		BookingResponse response = new BookingResponse();
		StatusDescription status = new StatusDescription();
		response.setStatus(status);
		try {
			Optional<BookingModel> booking = bookingRepo.findById(bookingId);
			if (booking.isPresent()) {
				logger.info(requestId + "-- Cancel Booking Request - Booking Found!");
				BookingModel hotelBooking = booking.get();
				if (!hotelBooking.getStatus().equals("CANCELLED")) {
					hotelBooking.setStatus("CANCELLED");
					bookingRepo.save(hotelBooking);
					response.setBooking(hotelBooking);
					status.setStatus(200);
					status.setDescription("Booking Cancelled!");
					logger.info(requestId + "-- Cancel Booking Request - Booking Cancelled!");
				} else {
					status.setStatus(200);
					status.setDescription("Booking Already Cancelled!");
					logger.info(requestId + "-- Cancel Booking Request - Booking Already Cancelled!");
				}
			} else {
				status.setStatus(201);
				status.setDescription("Booking Id Invalid!");
				logger.info(requestId + "-- Cancel Booking Request - Booking Id Invalid!");
			}
		} catch (Exception e) {
			status.setStatus(500);
			status.setDescription("Server Error!");
			logger.error(requestId + "-- Cancel Booking Request - " + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	public BookingResponse viewBooking(Long bookingId, String requestId) {
		BookingResponse response = new BookingResponse();
		StatusDescription status = new StatusDescription();
		response.setStatus(status);
		try {
			Optional<BookingModel> booking = bookingRepo.findById(bookingId);
			if (booking.isPresent()) {
				logger.info(requestId + "-- View Booking Request - Booking Found!");
				BookingModel hotelBooking = booking.get();
				response.setBooking(hotelBooking);
				status.setStatus(200);
				status.setDescription("Booking Details Fetched!");
			} else {
				status.setStatus(201);
				status.setDescription("Invalid Booking Id!");
				logger.info(requestId + "-- View Booking Request - Booking Not Found!");
			}
		} catch (Exception e) {
			status.setStatus(500);
			status.setDescription("Server Error!");
			logger.error(requestId + "-- View Booking Request - " + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

}
