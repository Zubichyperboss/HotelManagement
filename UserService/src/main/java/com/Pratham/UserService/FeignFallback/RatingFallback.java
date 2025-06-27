package com.Pratham.UserService.FeignFallback;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.Pratham.UserService.Feign.RatingClient;
import com.Pratham.UserService.Model.HotelModel;
import com.Pratham.UserService.Model.RatingWrapper;

@Component
public class RatingFallback implements RatingClient {

	@Override
	public ResponseEntity<List<RatingWrapper>> getUserRating(int uid) {
		// TODO Auto-generated method stub
        HotelModel dummyHotel = new HotelModel(
                0,
                "Unknown Hotel",
                "N/A",
                "Hotel details unavailable"
            );

            RatingWrapper fallbackRating = new RatingWrapper(
                0,        // rating id
                0,        // hotel id
                uid,      // user id
                0,        // rating
                "Rating Service Unavailable",
                dummyHotel
            );


		return new ResponseEntity<>(List.of(fallbackRating),HttpStatus.OK);
	}

}
