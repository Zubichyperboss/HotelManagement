package com.Pratham.HotelService.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Pratham.HotelService.Model.HotelModel;

public interface HotelService {
	
	// create hotel
	ResponseEntity<HotelModel> createHotel(HotelModel hotel);
	
	// create hotels
	ResponseEntity<List<HotelModel>> createHotels(List<HotelModel> hotel);
	
	//get single hotel
	ResponseEntity<HotelModel> getHotel(int id);
	
	//get all hotel
	ResponseEntity<List<HotelModel>> getHotels();
	
	//delete Hotel
	ResponseEntity<String> deleteHotel(int id);

}
