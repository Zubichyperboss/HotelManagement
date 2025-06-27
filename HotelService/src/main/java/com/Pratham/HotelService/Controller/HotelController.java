package com.Pratham.HotelService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pratham.HotelService.Model.HotelModel;
import com.Pratham.HotelService.Service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	HotelService hService;
	
	@PostMapping("/create")
	public ResponseEntity<HotelModel> createHotel(@RequestBody HotelModel hotel){
		return hService.createHotel(hotel);
	}
	
	@PostMapping("/createHotels")
	public ResponseEntity<List<HotelModel>> createHotel(@RequestBody List<HotelModel> hotel){
		return hService.createHotels(hotel);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<HotelModel> getHotel(@PathVariable int id){
		return hService.getHotel(id);
	}
	
	@GetMapping("/getHotels")
	public ResponseEntity<List<HotelModel>> getHotels(){
		return hService.getHotels();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteHotel(@PathVariable int id){
		return hService.deleteHotel(id);
	}
	
}
