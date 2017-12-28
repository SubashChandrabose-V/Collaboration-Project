package org.com.dao;

import org.com.model.ProfilePicture;

public interface ProfilePictureDAO {
	
	void uploadProfilePic(ProfilePicture profilePicture);
	
	ProfilePicture getProfilePic(String username);

}
