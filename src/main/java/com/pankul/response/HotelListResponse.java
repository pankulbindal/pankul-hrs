package com.pankul.response;

import java.util.List;

import com.pankul.models.HotelsModel;

public class HotelListResponse {

	private StatusDescription status;
	private List<HotelsModel> hotels;

	public StatusDescription getStatus() {
		return status;
	}

	public void setStatus(StatusDescription status) {
		this.status = status;
	}

	public List<HotelsModel> getHotels() {
		return hotels;
	}

	public void setHotels(List<HotelsModel> hotels) {
		this.hotels = hotels;
	}

	@Override
	public String toString() {
		return "HotelListResponse [status=" + status + ", hotels=" + hotels + "]";
	}
}
