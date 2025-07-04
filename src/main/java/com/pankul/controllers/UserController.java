package com.pankul.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pankul.response.UserResponse;
import com.pankul.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller class for Users related operations.
 */

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	public static Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Handles HTTP requests to search for user details by user ID.
	 * <p>
	 * This endpoint retrieves user information for the specified user ID. It logs
	 * the request details, delegates the search operation to the user service
	 * implementation, and returns a {@link UserResponse} containing the user
	 * details.
	 * </p>
	 *
	 * @param userId  the unique identifier of the user to search for, provided as a
	 *                request parameter
	 * @param request the {@link HttpServletRequest} containing request metadata
	 *                (used for logging and tracking)
	 * @return a {@link ResponseEntity} containing the user details and HTTP status
	 *
	 * @see UserResponse
	 */
	@RequestMapping("/users/search")
	public ResponseEntity<UserResponse> searchUser(@RequestParam Long userId, HttpServletRequest request) {
		String requestId = request.getRequestId();
		logger.info(requestId + "-- Request Received to Search User Details from Ip - " + request.getRemoteAddr());
		UserResponse response = new UserResponse();
		response = userServiceImpl.searchUserDetails(userId, requestId);
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}

}
