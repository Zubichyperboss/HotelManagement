package com.Pratham.UserService.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Pratham.UserService.FeignFallback.RatingFallback;
import com.Pratham.UserService.Model.RatingWrapper;



@FeignClient(name="RATINGSERVICE", url = "http://localhost:8082", fallback = RatingFallback.class)
public interface RatingClient {
	
	@GetMapping("rating/getRatingUser/{uid}")
 ResponseEntity<List<RatingWrapper>> getUserRating(@PathVariable int uid);

}
