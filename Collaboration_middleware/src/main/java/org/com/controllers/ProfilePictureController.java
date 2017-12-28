package org.com.controllers;

import javax.servlet.http.HttpSession;

import org.com.model.Error;
import org.com.model.ProfilePicture;
import org.com.service.ProfilePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class ProfilePictureController {

	@Autowired
	private ProfilePictureService profilePictureService;
	
	@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePic(@RequestParam CommonsMultipartFile image, HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null) {
			Error error = new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		} 
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setUsername(username);
		profilePicture.setImage(image.getBytes());
		profilePictureService.uploadProfilePicture(profilePicture);
		return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getimage/{username}", method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePicture(@PathVariable String username,HttpSession session) {
		String logInUsername=(String)session.getAttribute("username");
		if(logInUsername==null) {
			return null;
		}
		ProfilePicture profilePicture=profilePictureService.getProfilePicture(username);
		if(profilePicture==null) //is profilepic for the logged in user
			return null;
		else
			return profilePicture.getImage(); //image of the user
	}

}
