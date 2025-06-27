package com.Pratham.HotelService.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Pratham.HotelService.Exception.ResourceNotFoundException;
import com.Pratham.HotelService.Model.HotelModel;
import com.Pratham.HotelService.Repositiory.HotelRepo;
import com.Pratham.HotelService.Service.HotelService;

@Service
public class HotelImpl implements HotelService {

	@Autowired
	HotelRepo hRepo;
	
	@Override
	public ResponseEntity<HotelModel> createHotel(HotelModel hotel) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(hRepo.save(hotel),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<HotelModel>> createHotels(List<HotelModel> hotel) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(hRepo.saveAll(hotel),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<HotelModel> getHotel(int id) {
		// TODO Auto-generated method stub
	
			HotelModel hm=hRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("camt find the id: "+id+" in dhotel db"));
	
		return new ResponseEntity<>(hm,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<HotelModel>> getHotels() {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(hRepo.findAll(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteHotel(int id) {
		// TODO Auto-generated method stub
		if(hRepo.existsById(id)) {
			hRepo.deleteById(id);
		}else {
			throw new ResourceNotFoundException("user is not there in  dHotel db and id is  " + id);
		}
		return new ResponseEntity<>("deleted",HttpStatus.OK);
	}

}
