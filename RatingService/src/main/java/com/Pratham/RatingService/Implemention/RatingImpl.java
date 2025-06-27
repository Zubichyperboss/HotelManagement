package com.Pratham.RatingService.Implemention;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Pratham.RatingService.Exception.ResourceNotFoundException;
import com.Pratham.RatingService.Model.RatingModel;
import com.Pratham.RatingService.Repositiory.RatingRepo;
import com.Pratham.RatingService.Serice.RatingService;
import com.Pratham.RatingService.dto.UpdateRating;

@Service
public class RatingImpl implements RatingService {
	
	@Autowired
	RatingRepo rRepo;

	@Override
	public ResponseEntity<RatingModel> createRating(RatingModel rating) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(rRepo.save(rating),HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<List<RatingModel>> createRatings(List<RatingModel> rating) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(rRepo.saveAll(rating),HttpStatus.ACCEPTED);
	}
	
	@Override
	public ResponseEntity<RatingModel> getRating(int id) {
		// TODO Auto-generated method stub
		RatingModel rm=rRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("camt find the id: "+id+" in dhotel db"));
		return new ResponseEntity<>(rm,HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<List<RatingModel>> getRatings() {
		// TODO Auto-generated method stub
		return new ResponseEntity<>(rRepo.findAll(),HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<List<RatingModel>> getUserRatingsById(int uid) {
		// TODO Auto-generated method stub
		if(rRepo.existsById(uid)) {
			
			return new ResponseEntity<>(rRepo.findByUid(uid),HttpStatus.ACCEPTED);
		}else {
			throw new ResourceNotFoundException("user is not there iin databsase id " + uid);
		}
	}
	
	

	@Override
	public ResponseEntity<List<RatingModel>> getHotelRatingById(int hid) {
		// TODO Auto-generated method stub
		if(rRepo.existsById(hid)) {
			
			return new ResponseEntity<>(rRepo.findByHid(hid),HttpStatus.ACCEPTED);
		}else {
			throw new ResourceNotFoundException("user is not there iin databsase id " + hid);
		}
	}

	@Override
	public ResponseEntity<RatingModel> updaeHotelRatingById(UpdateRating ur, int id) {
		// TODO Auto-generated method stub
		
		
		RatingModel rm=rRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("camt find the id: "+id+" in dhotel db"));
		
		
		if (ur.getHid() != 0)
		    rm.setHid(ur.getHid());

		if (ur.getUid() != 0)
		    rm.setUid(ur.getUid());

		if (ur.getRating() != 0)
		    rm.setRating(ur.getRating());

		if (ur.getFeedback() != null && !ur.getFeedback().isBlank())
		    rm.setFeedback(ur.getFeedback());
		
		rRepo.save(rm);

		
		return new ResponseEntity<>(rm,HttpStatus.OK);
	}


}
