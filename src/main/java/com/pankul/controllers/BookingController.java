package com.pankul.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pankul.models.BookingModel;
import com.pankul.response.BookingResponse;
import com.pankul.service.BookingServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller class for Booking related operations.
 */
@Controller
@RequestMapping("/api")
public class BookingController {

	@Autowired
	BookingServiceImpl bookingService;

	public final Logger logger = LoggerFactory.getLogger(BookingController.class);

	/**
	 * Handles HTTP POST requests to create a new booking.
	 * <p>
	 * This endpoint receives booking details in the request body, processes the
	 * booking, and returns a {@link BookingResponse} indicating the outcome.
	 * </p>
	 *
	 * @param bookingDetails the details of the booking to be created, parsed from
	 *                       the request body
	 * @param request        the {@link HttpServletRequest} object containing
	 *                       request metadata (used for logging and tracking)
	 * @return a {@link ResponseEntity} containing the booking response and HTTP
	 *         status
	 *
	 * @see BookingModel
	 * @see BookingResponse
	 */
	@PostMapping("/booking/create")
	public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingModel bookingDetails,
			HttpServletRequest request) {
		String requestId = request.getRequestId();
		logger.info(requestId + "-- Request Received to create booking from Ip - " + request.getRemoteAddr());
		BookingResponse response = new BookingResponse();
		response = bookingService.createBooking(bookingDetails, requestId);
		return new ResponseEntity<BookingResponse>(response, HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to cancel an existing booking.
	 * <p>
	 * This endpoint cancels the booking with the specified booking ID. It logs the
	 * request information, delegates the cancellation to the booking service, and
	 * returns a {@link BookingResponse} indicating the result.
	 * </p>
	 *
	 * @param bookingId the unique identifier of the booking to cancel, passed as a
	 *                  request parameter
	 * @param request   the {@link HttpServletRequest} containing request metadata
	 *                  (used for logging and tracking)
	 * @return a {@link ResponseEntity} containing the booking cancellation response
	 *         and HTTP status
	 *
	 * @see BookingResponse
	 */
	@GetMapping("/booking/cancel")
	public ResponseEntity<BookingResponse> cancelBooking(@RequestParam Long bookingId, HttpServletRequest request) {
		String requestId = request.getRequestId();
		logger.info(requestId + "-- Request Received to create booking from Ip - " + request.getRemoteAddr());
		BookingResponse response = new BookingResponse();
		response = bookingService.cancelBooking(bookingId, requestId);
		return new ResponseEntity<BookingResponse>(response, HttpStatus.OK);
	}

	/**
	 * Handles HTTP GET requests to view the details of a specific booking.
	 * <p>
	 * This endpoint retrieves the booking information for the given booking ID. It
	 * logs the request, delegates the retrieval to the booking service, and returns
	 * a {@link BookingResponse} containing the booking details.
	 * </p>
	 *
	 * @param bookingId the unique identifier of the booking to view, provided as a
	 *                  request parameter
	 * @param request   the {@link HttpServletRequest} containing request metadata
	 *                  (used for logging and tracking)
	 * @return a {@link ResponseEntity} containing the booking details and HTTP
	 *         status
	 *
	 * @see BookingResponse
	 */
	@GetMapping("/booking/view")
	public ResponseEntity<BookingResponse> viewBooking(@RequestParam Long bookingId, HttpServletRequest request) {
		String requestId = request.getRequestId();
		logger.info(requestId + "-- Request Received to create booking from Ip - " + request.getRemoteAddr());
		BookingResponse response = new BookingResponse();
		response = bookingService.viewBooking(bookingId, requestId);
		return new ResponseEntity<BookingResponse>(response, HttpStatus.OK);
	}

}
