package com.Pratham.RatingService.Repositiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pratham.RatingService.Model.RatingModel;

@Repository
public interface RatingRepo extends JpaRepository<RatingModel,Integer> {

	List<RatingModel> findByUid(int uid);

	List<RatingModel> findByHid(int hid);



}
