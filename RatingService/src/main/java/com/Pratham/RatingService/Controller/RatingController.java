package com.Pratham.RatingService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pratham.RatingService.Model.RatingModel;
import com.Pratham.RatingService.Serice.RatingService;
import com.Pratham.RatingService.dto.UpdateRating;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	RatingService rService;
	
	@PostMapping("/create")
	public ResponseEntity<RatingModel> createRating(@RequestBody RatingModel rating){
		return rService.createRating(rating);
	}
	
	@PostMapping("/createRatings")
	public ResponseEntity<List<RatingModel>> createRatings(@RequestBody List<RatingModel> ratings){
		return rService.createRatings(ratings);
	}
	
	@GetMapping("/getRating/{id}")
	public ResponseEntity<RatingModel> getRating(@PathVariable int id){
		return rService.getRating(id);
	}
	
	@GetMapping("/getRatings")
	public ResponseEntity<List<RatingModel>> getRatings(){
		return rService.getRatings();
	}
	
	@GetMapping("/getRatingUser/{uid}")
	public ResponseEntity<List<RatingModel>> getUserRating(@PathVariable int uid){
		return rService.getUserRatingsById(uid);
	}
	
	@GetMapping("/getRatingHotel/{hid}")
	public ResponseEntity<List<RatingModel>> getHotelRating(@PathVariable int hid){
		return rService.getHotelRatingById(hid);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<RatingModel> updateHotelRating(@RequestBody UpdateRating ur,@PathVariable int id){
		return rService.updaeHotelRatingById(ur,id);
	}
	
}
