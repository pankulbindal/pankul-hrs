package com.pankul.response;

import com.pankul.models.BookingModel;

public class BookingResponse {

	private BookingModel booking;
	private StatusDescription status;
	public BookingModel getBooking() {
		return booking;
	}
	public void setBooking(BookingModel booking) {
		this.booking = booking;
	}
	public StatusDescription getStatus() {
		return status;
	}
	public void setStatus(StatusDescription status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BookingResponse [booking=" + booking + ", status=" + status + "]";
	}
	
	
}
