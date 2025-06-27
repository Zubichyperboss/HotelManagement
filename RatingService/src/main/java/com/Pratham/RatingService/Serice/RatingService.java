package com.Pratham.RatingService.Serice;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Pratham.RatingService.Model.RatingModel;
import com.Pratham.RatingService.dto.UpdateRating;

public interface RatingService {

	
	//create raiting 
	ResponseEntity<RatingModel> createRating(RatingModel rating);
	
	//create raititngs
	ResponseEntity<List<RatingModel>> createRatings(List<RatingModel> rating);
	
	//find by id
	ResponseEntity<RatingModel> getRating(int id);
	
	//get all raitings
	ResponseEntity<List<RatingModel>> getRatings();
	
	//get rating by user id
    ResponseEntity<List<RatingModel>> getUserRatingsById(int uid);
	//List<RatingModel> getUserRatingsById(int uid);
	
	//get hotel raiting
	ResponseEntity<List<RatingModel>> getHotelRatingById(int hid);

	ResponseEntity<RatingModel> updaeHotelRatingById(UpdateRating ur, int id);
}
