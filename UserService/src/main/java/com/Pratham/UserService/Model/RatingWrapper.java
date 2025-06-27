package com.Pratham.UserService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingWrapper {

	
	private int id;
	private int hid;
	private int uid;
	private int rating;
	private String feedback;
	private HotelModel hotel;
	

	
}
