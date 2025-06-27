package com.Pratham.UserService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelModel {

	private int id;
	private String name;
	private String location;
	private String about;
	
}
