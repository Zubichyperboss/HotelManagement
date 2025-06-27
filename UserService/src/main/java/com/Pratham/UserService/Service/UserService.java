package com.Pratham.UserService.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Pratham.UserService.Model.UserModel;
import com.Pratham.UserService.dto.LoginUser;
import com.Pratham.UserService.dto.UpdateUser;

public interface UserService {

	//create single user
	ResponseEntity<UserModel> saveUser(UserModel user);
	
	//create multiple USer
	ResponseEntity<List<UserModel>> saveUsers(List<UserModel> users);
	
	//get all user
	ResponseEntity<List<UserModel>> getAllUser();
	ResponseEntity<List<UserModel>> getUserFallback1();
	
	//get single user
	ResponseEntity<UserModel> getSingleUSer(int id);
	ResponseEntity<UserModel> getUserFallback(int id);
	
	//delete user
	ResponseEntity<String> deleteUser(int id);
	
	//update user name
	ResponseEntity<Boolean> updateUser(int id,UpdateUser user);

	ResponseEntity<String> login(LoginUser lu);
	
	


	
}
