package com.Pratham.UserService.FeignFallback;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.Pratham.UserService.Feign.HotelClient;
import com.Pratham.UserService.Model.HotelModel;

@Component
public class HotelFallback implements HotelClient {

	@Override
	public ResponseEntity<HotelModel> getHotel(int id) {
		// TODO Auto-generated method stub
		 HotelModel fallbackHotel = new HotelModel(
		            id,
		            "Hotel Service Unavailable",
		            "N/A",
		            "This is a fallback hotel returned because HotelService is down."
		        );
		return new ResponseEntity<>(fallbackHotel,HttpStatus.OK);
	}

}
