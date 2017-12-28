package org.com.service;

import java.util.List;

import org.com.dao.FriendDAO;
import org.com.model.Friend;
import org.com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendDAO friendDAO;
	@Override
	public List<User> listOfSuggestedUsers(String username) {
		return friendDAO.listOfSuggestedUsers(username);
	}
	@Override
	public void friendRequest(Friend friend) {
		
		friendDAO.friendRequest(friend);
	}
	@Override
	public List<Friend> pendingRequests(String toId) {
		return friendDAO.pendingRequests(toId);
	}
	@Override
	public void updateFriendRequest(Friend friend) {
		friendDAO.updatePendingRequest(friend);
	}
	@Override
	public List<String> listOfFriends(String username) {
		return friendDAO.listOfFriends(username);
	}
}
