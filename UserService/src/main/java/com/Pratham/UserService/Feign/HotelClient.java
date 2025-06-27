package com.Pratham.UserService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Pratham.UserService.FeignFallback.HotelFallback;
import com.Pratham.UserService.Model.HotelModel;



@FeignClient(name="HOTELSERVICE",url = "http://localhost:8081", fallback = HotelFallback.class)
public interface HotelClient {

	@GetMapping("hotel/get/{id}")
	public ResponseEntity<HotelModel> getHotel(@PathVariable int id);
	

	
}
