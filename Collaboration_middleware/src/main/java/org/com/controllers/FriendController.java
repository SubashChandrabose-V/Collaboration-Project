package org.com.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.com.model.Error;
import org.com.model.Friend;
import org.com.model.User;
import org.com.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FriendController {

	@Autowired
	private FriendService friendService;
	
	@RequestMapping(value="/getsuggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> getSuggestedUsers(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null) {
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<User> suggestedUsers=friendService.listOfSuggestedUsers(username);
		return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
	}
	
	@RequestMapping(value="/friendrequest/{toId}")
	public ResponseEntity<?> friendRequest(@PathVariable String toId, HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null) {
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Friend friend=new Friend();
		friend.setFromId(username);
		friend.setToId(toId);
		friend.setStatus('P');
		friendService.friendRequest(friend); //insert into friend table
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
	public ResponseEntity<?> pendingRequests(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null) {
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}

		List<Friend> pendingRequests=friendService.pendingRequests(username);
		return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/updatependingrequest", method=RequestMethod.PUT)
	public ResponseEntity<?> updatePendingRequest(@RequestBody Friend friend, HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null) {
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		System.out.println(friend.getFromId() + " " + friend.getStatus());
		friendService.updateFriendRequest(friend); //update status to A or Delete 
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/friendslist", method=RequestMethod.GET)
	public ResponseEntity<?> listOfFriends(HttpSession session) {
		String username=(String)session.getAttribute("username");
		if(username==null) {
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<String> friends=friendService.listOfFriends(username);
		return new ResponseEntity<List<String>>(friends,HttpStatus.OK);
		
	}
	

}
