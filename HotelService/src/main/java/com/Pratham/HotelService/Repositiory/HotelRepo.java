package com.Pratham.HotelService.Repositiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pratham.HotelService.Model.HotelModel;

@Repository
public interface HotelRepo extends JpaRepository<HotelModel,Integer> {

}
