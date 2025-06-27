package com.Pratham.UserService.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.Pratham.UserService.Model.UserModel;
import com.Pratham.UserService.dto.UpdateUser;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
	
	// This will update the target object in-place (partial update)
	 @Mapping(target = "raitings", ignore = true)
    void updateUserFromDto(UpdateUser source, @MappingTarget UserModel target);

}
