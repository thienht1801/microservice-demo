package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDTO;
import com.example.service.UserService;

@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

	@Autowired
    UserService userService;
	
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> userList = userService.getAllUser();
        return new ResponseEntity<List<UserDTO>>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(@RequestParam(value = "userId", required = true) Long userId) {
        UserDTO UserDTO = userService.getUserById(userId);
        return new ResponseEntity<UserDTO>(UserDTO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/userId", method = RequestMethod.GET)
    public ResponseEntity<List<Long>> getUser() {
        List<Long> userIdList = userService.getAllUserId();
        return new ResponseEntity<List<Long>>(userIdList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Long> addUser(@RequestBody UserDTO UserDTO) {
        Long userId = userService.addUser(UserDTO);
        return new ResponseEntity<Long>(userId, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<Long> updateUser(@RequestBody UserDTO UserDTO) {
        userService.updateUser(UserDTO);
        return new ResponseEntity<Long>(UserDTO.getId(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> deleteUser(@PathVariable(value = "userId") Long userId) {
    	userService.deleteUser(userId);
    	return new ResponseEntity<Long>(userId, HttpStatus.OK);       
    }    	
}
