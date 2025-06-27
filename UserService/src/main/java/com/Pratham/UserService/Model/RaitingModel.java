package com.Pratham.UserService.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

//import java.util.List;

import lombok.AllArgsConstructor;
//import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaitingModel {

	//@JsonProperty("id")
	private int raitingId;
	
	//@JsonProperty("uid")
	private int userId;
	
	//@JsonProperty("hid")
	private int hotelId;
	
	//@JsonProperty("rating")
	private int raiting;
	
	private String feedback;
	private HotelModel hotel;
	
	
	
}
