package com.Pratham.RatingService.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rating")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int hid;
	private int uid;
	private int rating;
	private String feedback;
	
	
	
}
