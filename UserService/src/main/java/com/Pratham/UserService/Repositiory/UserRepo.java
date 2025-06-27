package com.Pratham.UserService.Repositiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.Pratham.UserService.Model.UserModel;

public interface UserRepo extends JpaRepository<UserModel,Integer> {

	UserModel findByName(String uname);
	
//	@Modifying
//	@Transactional
//	@Query("update UserModel u set u.name= :name where u.id= :id")
//	int updateUserName(@Param("id") int id,@Param("name") String name);
//	
//	@Modifying
//	@Transactional
//	@Query("update UserModel u set u.email= :email where u.id= :id")
//	int updateUserEmail(@Param("id") int id,@Param("email") String email);
//	
//	@Modifying
//	@Transactional
//	@Query("update UserModel u set u.about= :about where u.id= :id")
//	int updateUserAbout(@Param("id") int id,@Param("about") String about);
//	
	
	

}
