package com.Pratham.UserService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pratham.UserService.Model.UserModel;
import com.Pratham.UserService.Service.UserService;
import com.Pratham.UserService.dto.LoginUser;
import com.Pratham.UserService.dto.UpdateUser;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired

	UserService uService;
	
	
	@PostMapping("/register")
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
		return uService.saveUser(user);
		
	}
	
	@PostMapping("/login")
	ResponseEntity<String> login(@RequestBody LoginUser lu){
		return uService.login(lu);
	}
	
	@PostMapping("/registerMultiple")
	public ResponseEntity<List<UserModel>> createUsers(@RequestBody List<UserModel> users){
		return uService.saveUsers(users);
	}
	
	@GetMapping("/getUsers")
	@CircuitBreaker(name="getUserBreaker1",fallbackMethod="getUserFallback1")
	@Retry(name="getUserRetry1",fallbackMethod="getUserFallback1")
	@RateLimiter(name="getUserRateLimiter1",fallbackMethod="getUserFallback1")
	public ResponseEntity<List<UserModel>> fetchAllUsers(){
		return uService.getAllUser();
	}
	
	public ResponseEntity<List<UserModel>> getUserFallback1(Exception ex){
		System.out.println("fallback in getUsers due to "+ex.getMessage());
		return uService.getUserFallback1();
	}
	
	//int count=0;
	@GetMapping("/getUser/{id}")
	@CircuitBreaker(name="getUserBreaker",fallbackMethod="getUserFallback")
	@Retry(name="getUserRetry",fallbackMethod="getUserFallback")
	@RateLimiter(name="getUserRateLimiter",fallbackMethod="getUserFallback")
	public ResponseEntity<UserModel> fetchSingleUser(@PathVariable int id){
		//count++;
		//System.out.println(count);
	
		return uService.getSingleUSer(id);
	}
	
	public ResponseEntity<UserModel> getUserFallback(int id,Exception ex){
		System.out.println("fallback in getUser due to "+ex.getMessage());
		return uService.getUserFallback(id);
	}
	
	
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id){
		return uService.deleteUser(id);
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<Boolean> updateUser(@PathVariable int id,@RequestBody UpdateUser user){
		return uService.updateUser(id,user);
	}
	
	
}
