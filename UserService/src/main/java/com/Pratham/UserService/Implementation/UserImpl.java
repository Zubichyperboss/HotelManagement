package com.Pratham.UserService.Implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Pratham.UserService.Exception.ResourceNotFoundException;
import com.Pratham.UserService.Feign.HotelClient;
import com.Pratham.UserService.Feign.RatingClient;
import com.Pratham.UserService.Mapper.UserMapper;
import com.Pratham.UserService.Model.HotelModel;
import com.Pratham.UserService.Model.RaitingModel;
import com.Pratham.UserService.Model.RatingWrapper;
import com.Pratham.UserService.Model.UserModel;
import com.Pratham.UserService.Repositiory.UserRepo;
import com.Pratham.UserService.Service.UserService;
import com.Pratham.UserService.dto.LoginUser;
import com.Pratham.UserService.dto.UpdateUser;
import com.security.customsec.JwtUtil;

@Service
public class UserImpl implements UserService {


	UserRepo uRepo;
	
	
	HotelClient hClient;
	
UserMapper mapper;
	RatingClient rClient;
	
	JwtUtil jwtUtil;
	PasswordEncoder pe;
	AuthenticationManager aManager;
	
	UserImpl(UserRepo uRepo,HotelClient hClient,RatingClient rClient,PasswordEncoder pe,AuthenticationManager aManager,JwtUtil jwtUtil
			,UserMapper mapper){
		this.uRepo=uRepo;
		this.hClient=hClient;
		this.rClient=rClient;
		this.pe=pe;
		this.aManager=aManager;
		this.jwtUtil=jwtUtil;
		this.mapper=mapper;

		}

	@Override
	public ResponseEntity<UserModel> saveUser(UserModel user) {
		// TODO Auto-generated method stub
		
		user.setPass(pe.encode(user.getPass()));
		UserModel um = uRepo.save(user);
		
		return new ResponseEntity<>(um, HttpStatus.ACCEPTED);
	}
	
	@Override
	public ResponseEntity<List<UserModel>> saveUsers(List<UserModel> users){
		
		for(int i=0;i<users.size();i++) {
			UserModel  um=users.get(i);
			um.setPass(pe.encode(um.getPass()));
		}
		
		return new ResponseEntity<>(uRepo.saveAll(users),HttpStatus.CREATED);
	}
	

	@Override
	public ResponseEntity<List<UserModel>> getAllUser() {
		// TODO Auto-generated method stub
		List<UserModel> lum = uRepo.findAll();
		
		for(int i=0;i<lum.size();i++) {
		UserModel um=lum.get(i);
		int id=um.getId();
		
		List<RatingWrapper> rw=rClient.getUserRating(id).getBody();
		
		if(rw.size()!=0) {
			for(int j=0;j<rw.size();j++) {
				RatingWrapper ratingwrapper=rw.get(j);
				ratingwrapper.setHotel(hClient.getHotel(ratingwrapper.getHid()).getBody());
				
			}
			
		}
		
			//ArrayList urating=rTemplate.getForObject(String.format("http://localhost:8082/rating/getRatingUser/%d",id),ArrayList.class);
		um.setRaitings(rw);
			
		}
		
		return new ResponseEntity<>(lum, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserModel>> getUserFallback1() {
		// TODO Auto-generated method stub
		
		UserModel um=UserModel.builder()
				.id(0)
				.name("dummy")
				.email("dummy@gmial.com")
				.pass("dummy")
				.role("null")
				.build();
		return new ResponseEntity<>(List.of(um),HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<UserModel> getSingleUSer(int id) {
		// TODO Auto-generated method stub

		UserModel um = uRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not found in databse for id " + id));
		List<RatingWrapper> rm=rClient.getUserRating(id).getBody();
		
		if(rm.size()!=0) {
		for(int i=0;i<rm.size();i++) {
			RatingWrapper rw=rm.get(i);
			HotelModel hm=hClient.getHotel(rw.getHid()).getBody();
			//System.out.println(hm);
			rw.setHotel(hm);
		}
		}
		um.setRaitings(rm);
		
		//um.setRaitings(rm);
		
		//ArrayList<RaitingModel> urating=rTemplate.getForObject(String.format("http://localhost:8082/rating/getRatingUser/%d",id),ArrayList.class);
//		for(int i=0;i<aurating.length;i++) {
//			System.out.println(aurating[i]);
//			
//		}
		
	//	List<RaitingModel> urating=Arrays.stream(aurating).toList();
		
			//System.out.println(urating);
//			for(int i=0;i<urating.size();i++) {
//				RaitingModel rm=urating.get(i);
//				HotelModel hm=rTemplate.getForObject(String.format("http://localhost:8081/hotel/get/%d",id),HotelModel.class);
//				rm.setHotel(hm);
//			}
			
		
		//List<RaitingModel> urating=Arrays.stream(aurating).toList();
		
	//	um.setRaitings(urating);
		
		
		return new ResponseEntity<>(um, HttpStatus.OK);
	}

	
	@Override
	public ResponseEntity<UserModel> getUserFallback(int id) {
		// TODO Auto-generated method stub
		UserModel um=UserModel.builder()
				.id(id)
				.name("dummy")
				.email("dummy@gmial.com")
				.pass("dummy")
				.role("null")
				.build();
		
		return new ResponseEntity<>(um,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> deleteUser(int id) {
		// TODO Auto-generated method stub
		if (uRepo.existsById(id)) {

			uRepo.deleteById(id);
		} else {
			throw new ResourceNotFoundException("user is not there iin databsase id " + id);
		}

		return new ResponseEntity<>("deleted", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> updateUser(int id, UpdateUser user) {
		// TODO Auto-generated method stub
		if (uRepo.existsById(id)) {
			UserModel um=uRepo.findById(id).orElseThrow(()->new  ResourceNotFoundException("no id found"));
			mapper.updateUserFromDto(user, um);
			uRepo.save(um);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("user is not there iin databsase id " + id);
		}
		
		
	}

	@Override
	public ResponseEntity<String> login(LoginUser lu) {
		// TODO Auto-generated method stub
		Authentication auth=aManager.authenticate(new UsernamePasswordAuthenticationToken(lu.getUname(),lu.getUpass()));
		
		if(auth.isAuthenticated()) {
			//System.out.println("hii");
			UserModel um=uRepo.findByName(lu.getUname());
			String name=jwtUtil.generateToken(lu.getUname(),um.getRole());
			//System.out.println(name);
			return new ResponseEntity<>(name,HttpStatus.OK);
		}
		
		return new ResponseEntity<>("failed",HttpStatus.OK);
	}





}
