package org.com.service;

import java.util.List;

import org.com.model.Friend;
import org.com.model.User;

public interface FriendService {

	List<User> listOfSuggestedUsers(String username);

	void friendRequest(Friend friend);
	
	List<Friend> pendingRequests(String toId);

	void updateFriendRequest(Friend friend);
	
	List<String> listOfFriends(String username);

}
