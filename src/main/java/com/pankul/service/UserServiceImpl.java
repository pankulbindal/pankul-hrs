package com.pankul.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankul.models.UsersModel;
import com.pankul.repo.UsersRepo;
import com.pankul.response.StatusDescription;
import com.pankul.response.UserResponse;

/**
 * Service implementation for user-related operations.
 * <p>
 * This service provides business logic to search for user details by user ID.
 * It interacts with the {@link UsersRepo} repository and handles response
 * construction, status codes, and logging for each operation.
 * </p>
 *
 * <p>
 * Example usage:
 * 
 * <pre>
 * UserResponse response = userServiceImpl.searchUserDetails(123L, "REQ-001");
 * </pre>
 * </p>
 *
 * @author pankulbindal
 */
@Service
public class UserServiceImpl {

	/**
	 * Repository for performing CRUD operations on user entities.
	 */
	@Autowired
	UsersRepo usersRepo;

	/**
	 * Logger instance for logging events in the {@link UserServiceImpl} class.
	 */
	public static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * Searches for user details by the given user ID.
	 * <p>
	 * Retrieves user information from the database. If the user exists, their
	 * details are returned in the {@link UserResponse}. If the user ID is invalid
	 * or not found, a status and description indicating an invalid user ID are set.
	 * In case of server errors, an appropriate status and message are set in the
	 * response. All operations are logged with the provided request ID.
	 * </p>
	 *
	 * @param userId    the unique identifier of the user to search for
	 * @param requestId the unique identifier of the request, used for logging and
	 *                  tracking
	 * @return a {@link UserResponse} containing the user details if found, or error
	 *         status otherwise
	 */
	public UserResponse searchUserDetails(Long userId, String requestId) {
		UserResponse response = new UserResponse();
		StatusDescription status = new StatusDescription();
		response.setStatus(status);
		try {
			Optional<UsersModel> userDetails = usersRepo.findById(userId);
			if (userDetails.isPresent()) {
				response.setUserDetails(userDetails.get());
				logger.info(requestId + "-- Search User Details - Results found with User Id - " + userId);
			} else {
				status.setStatus(201);
				status.setDescription("User Id Invalid!");
				logger.info(requestId + "-- Search User Details - User Id - " + userId + " is inavlid!");
			}
		} catch (Exception e) {
			status.setStatus(500);
			status.setDescription("Server Error!");
			logger.info(requestId + "-- Search User Details - " + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

}