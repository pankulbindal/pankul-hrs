package com.pankul;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pankul.models.BookingModel;
import com.pankul.models.HotelsModel;
import com.pankul.models.UsersModel;
import com.pankul.repo.BookingsRepo;
import com.pankul.repo.HotelsRepo;
import com.pankul.repo.UsersRepo;
import com.pankul.response.BookingResponse;
import com.pankul.service.BookingServiceImpl;

@SpringBootTest
class BookingServiceTest {

	@InjectMocks
	private BookingServiceImpl bookingService;

	@Mock
	private HotelsRepo hotelsRepo;

	@Mock
	private UsersRepo userRepo;

	@Mock
	private BookingsRepo bookingRepo;

	private BookingModel bookingRequest;
	private HotelsModel hotel;
	private UsersModel user;
	private String requestId = "req-1234";

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		// Initialize test data
		bookingRequest = new BookingModel();
		bookingRequest.setHotelId(1L);
		bookingRequest.setId(2L); // This is UserId for search in userRepo
		bookingRequest.setCheckInDate(LocalDate.of(2025, 8, 1));
		bookingRequest.setCheckOutDate(LocalDate.of(2025, 8, 3));

		hotel = new HotelsModel();
		hotel.setId(1L);
		hotel.setHotelPrice(100.0);

		user = new UsersModel();
		user.setId(2L);
	}

	@Test
    void testCreateBooking_Success() {
        when(hotelsRepo.findById(1L)).thenReturn(Optional.of(hotel));
        when(userRepo.findById(2L)).thenReturn(Optional.of(user));
        when(bookingRepo.save(any(BookingModel.class))).thenReturn(bookingRequest);

        BookingResponse response = bookingService.createBooking(bookingRequest, requestId);

        assertEquals(200, response.getStatus().getStatus());
        assertEquals("Booking Created!", response.getStatus().getDescription());
        assertNotNull(response.getBooking());
        assertEquals("CONFIRMED", response.getBooking().getStatus());
        assertEquals(200.0, response.getBooking().getTotalPrice()); // 2 days * 100.0
    }

	@Test
    void testCreateBooking_HotelNotFound() {
        when(hotelsRepo.findById(1L)).thenReturn(Optional.empty());

        BookingResponse response = bookingService.createBooking(bookingRequest, requestId);

        assertEquals(201, response.getStatus().getStatus());
        assertEquals("Hotel Id Incorrect!", response.getStatus().getDescription());
        assertNull(response.getBooking());
    }

	@Test
    void testCreateBooking_UserNotFound() {
        when(hotelsRepo.findById(1L)).thenReturn(Optional.of(hotel));
        when(userRepo.findById(2L)).thenReturn(Optional.empty());

        BookingResponse response = bookingService.createBooking(bookingRequest, requestId);

        assertEquals(201, response.getStatus().getStatus());
        assertEquals("User Id incorrect!", response.getStatus().getDescription());
        assertNull(response.getBooking());
    }

	@Test
	void testCreateBooking_InvalidDates() {
		bookingRequest.setCheckInDate(LocalDate.of(2025, 8, 5));
		bookingRequest.setCheckOutDate(LocalDate.of(2025, 8, 3)); // check-in after check-out

		when(hotelsRepo.findById(1L)).thenReturn(Optional.of(hotel));
		when(userRepo.findById(2L)).thenReturn(Optional.of(user));

		BookingResponse response = bookingService.createBooking(bookingRequest, requestId);

		assertEquals(201, response.getStatus().getStatus());
		assertEquals("Check out date should be after Check in date!", response.getStatus().getDescription());
		assertNull(response.getBooking());
	}

	@Test
	void testCreateBooking_ZeroDayBooking() {
		bookingRequest.setCheckInDate(LocalDate.of(2025, 8, 3));
		bookingRequest.setCheckOutDate(LocalDate.of(2025, 8, 3)); // same day

		when(hotelsRepo.findById(1L)).thenReturn(Optional.of(hotel));
		when(userRepo.findById(2L)).thenReturn(Optional.of(user));
		when(bookingRepo.save(any(BookingModel.class))).thenReturn(bookingRequest);

		BookingResponse response = bookingService.createBooking(bookingRequest, requestId);

		assertEquals(200, response.getStatus().getStatus());
		assertEquals("Booking Created!", response.getStatus().getDescription());
		assertEquals(100.0, response.getBooking().getTotalPrice());
	}

	@Test
    void testCreateBooking_ServerError() {
        when(hotelsRepo.findById(1L)).thenThrow(new RuntimeException("DB error"));

        BookingResponse response = bookingService.createBooking(bookingRequest, requestId);

        assertEquals(500, response.getStatus().getStatus());
        assertEquals("Server Error!", response.getStatus().getDescription());
        assertNull(response.getBooking());
    }
}