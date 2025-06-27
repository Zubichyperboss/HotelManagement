package com.Pratham.RatingService.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRating {
	
	private int hid;
	private int uid;
	private int rating;
	private String feedback;

}
