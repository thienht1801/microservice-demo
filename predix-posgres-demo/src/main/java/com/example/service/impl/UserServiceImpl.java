package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.repo.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    
	@Override
	@Transactional
	public List<UserDTO> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDtoList = new ArrayList<UserDTO>();
        if (null != userList && userList.size() > 0) {
        	userDtoList = userList.stream()
                    .map(item -> convertToDto(item))
                    .collect(Collectors.toList());
        }
        return userDtoList;
	}

	@Override
	@Transactional
	public List<Long> getAllUserId() {
        List<Long> userIdList = userRepository.getAllUserIds();
        return userIdList;
	}

	@Override
	@Transactional
	public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id);
        return convertToDto(user);
	}

	@Override
	@Transactional
	public Long addUser(UserDTO userDto) {
        if(null != getUserById(userDto.getId())) {
            throw new IllegalArgumentException("User Already Existed");
        }
        User user = ConvertToEntity(userDto);
        user = userRepository.save(user);
        return user.getId();
	}

	@Override
	@Transactional
	public void updateUser(UserDTO userDto) {
		User sensei = ConvertToEntity(userDto);
        if(null !=sensei) {
        	userRepository.save(sensei);
        }
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
        if(null != id) {
        	User removeUser = userRepository.findById(id);
        	if(null == removeUser){
        		throw new IllegalArgumentException("Could not find User with id: " + id);
        	}       	
        	userRepository.delete(removeUser);
        }

	}

    public UserDTO convertToDto(final User user) {
    	UserDTO userDto = null;
        if(null !=user) {
            userDto = new UserDTO();
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setAddress(user.getAddress());            
        }
        return userDto;
    }
    
    public User ConvertToEntity(final UserDTO userDto) {
        User user = null;
        if(null !=userDto) {
            if(null != userDto.getId()) {
            		user = userRepository.findOne(userDto.getId());
            		if(null == user){
            			user = new User();
            			user.setId(userDto.getId());
            		}                    
                    user.setFirstName(userDto.getFirstName());
                    user.setLastName(userDto.getLastName());
                    user.setAddress(userDto.getAddress());
            }
        }
        return user;
    }
}
