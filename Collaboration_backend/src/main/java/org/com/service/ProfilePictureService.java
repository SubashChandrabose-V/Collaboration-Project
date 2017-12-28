package org.com.service;

import org.com.model.ProfilePicture;

public interface ProfilePictureService {

	void uploadProfilePicture(ProfilePicture profilePicture);
	
	ProfilePicture getProfilePicture(String username);

}
