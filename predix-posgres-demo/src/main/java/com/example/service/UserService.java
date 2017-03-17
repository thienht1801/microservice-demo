/*
 * 
 */
package com.example.service;

import java.util.List;

import com.example.dto.UserDTO;

/**
 * The Interface SenseiService.
 */
public interface UserService {

    /**
     * Gets the sensei team.
     *
     * @return the sensei team
     */
    List<UserDTO> getAllUser();

    List<Long> getAllUserId();
    
    UserDTO getUserById(Long id);
	
	Long addUser(UserDTO userDto);

	void updateUser(UserDTO userDto);

	void deleteUser(Long id);
}

