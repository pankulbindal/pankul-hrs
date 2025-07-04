package com.pankul.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankul.models.HotelsModel;
import com.pankul.repo.HotelsRepo;
import com.pankul.response.HotelListResponse;
import com.pankul.response.StatusDescription;

@Service
public class HotelServiceImpl {
	
	@Autowired
	HotelsRepo hotelRepo;
	
	public static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

	public HotelListResponse searchHotels(String city) {
		HotelListResponse response = new HotelListResponse();
		StatusDescription status = new StatusDescription();
		response.setStatus(status);
		List<HotelsModel> hotels =  new ArrayList<HotelsModel>();
		try {
			hotels = hotelRepo.findByCityIgnoreCase(city);
			if(hotels.size() > 0) {
				logger.info(hotels.size() + " Hotels found in city - " + city);
				response.setHotels(hotels);
				status.setStatus(200);
				status.setDescription("Results Found!");
				return response;
			} else {
				status.setStatus(404);
				status.setDescription("No Results Found!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			status.setStatus(500);
			status.setDescription("Server Error!");
		}
		return response;
	}

	
	
	

}
