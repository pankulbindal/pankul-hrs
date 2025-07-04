package com.pankul.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pankul.response.HotelListResponse;
import com.pankul.service.HotelServiceImpl;

/**
 * Controller class for Hotels related operations.
 */
@Controller
@RequestMapping("/api")
public class HotelController {

	@Autowired
	HotelServiceImpl hotelService;

	/**
	 * Logger instance for logging events in the {@link HotelController} class.
	 * <p>
	 * This logger is used for recording informational messages, warnings, and
	 * errors that occur within the controller. It leverages the SLF4J API for
	 * logging.
	 * </p>
	 */
	public static final Logger logger = LoggerFactory.getLogger(HotelController.class);

	/**
	 * Handles HTTP GET requests to search for hotels in a specific city.
	 * <p>
	 * This endpoint retrieves a list of hotels located in the specified city. It
	 * logs the incoming request, delegates the search operation to the hotel
	 * service, and returns a {@link HotelListResponse} containing the search
	 * results.
	 * </p>
	 *
	 * @param city the name of the city to search hotels in, provided as a request
	 *             parameter
	 * @return a {@link ResponseEntity} containing the list of hotels and HTTP
	 *         status
	 *
	 * @see HotelListResponse
	 */
	@GetMapping("/hotels/search")
	public ResponseEntity<HotelListResponse> searchHotels(@RequestParam String city) {
		logger.info("Request received to Search Hotels in City - " + city);
		HotelListResponse response = new HotelListResponse();
		response = hotelService.searchHotels(city);
		return new ResponseEntity<HotelListResponse>(response, HttpStatus.OK);
	}

}
