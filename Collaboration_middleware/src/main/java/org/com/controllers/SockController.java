package org.com.controllers;

import java.util.ArrayList;
import java.util.List;

import org.com.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SockController {
	private List<String> users=new ArrayList<String>(); // to add users- list of usernames
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	//$stompClient.subscribe("app/join/"+scope.user,function(message){})-client side code
	
	@SubscribeMapping("/join/{username}")
	public List<String> join(@DestinationVariable("username") String username) {
		if(!users.contains(username))
			users.add(username); // ading a newly joined usename to the list
		
		messagingTemplate.convertAndSend("/topic/join",username); //to all other users in chat room
		
		//in frontend @stomclint .subscription{"/topic/join",function(mesaage)[]}
		return users;	//to newly joined user
	}
	
	//$stompclient.send("app/chat")
	@MessageMapping(value="/chat")
	public void chatRecevied(Chat chat) {
		if("all".equals(chat.getTo())) { // group chat
			messagingTemplate.convertAndSend("/queue/chats",chat);
		}
		else {
			
			
			messagingTemplate.convertAndSend("/queue/chats/"+chat.getFrom(),chat);
			messagingTemplate.convertAndSend("/queue/chats/"+chat.getTo(),chat);
		}
	}


}
