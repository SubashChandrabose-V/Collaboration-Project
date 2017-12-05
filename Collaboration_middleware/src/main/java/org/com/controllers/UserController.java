package org.com.controllers;

import org.com.model.Error;
import org.com.model.User;
import org.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	/*
	 * ?- Any type of data can be returned
	 */
	@RequestMapping(value="/registeruser",method=RequestMethod.POST)	
	public ResponseEntity<?> registerUser(@RequestBody User user){
		boolean result=userService.registerUser(user);
		if(result){
			return new ResponseEntity<User>(user,HttpStatus.OK);//200-299
			
		}
		else{
			Error error=new Error(1,"Unable to register user details");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
		
	}
	

}
