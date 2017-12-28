package org.com.dao;

import java.util.List;

import org.com.model.Friend;
import org.com.model.User;

public interface FriendDAO {
	
	List<User> listOfSuggestedUsers(String username);

	void friendRequest(Friend friend);

	List<Friend> pendingRequests(String toId);

	void updatePendingRequest(Friend friend);
	
	List<String> listOfFriends(String username);
}
