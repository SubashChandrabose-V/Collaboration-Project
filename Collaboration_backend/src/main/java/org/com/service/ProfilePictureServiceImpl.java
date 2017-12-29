package org.com.service;

import org.com.dao.ProfilePictureDAO;
import org.com.model.ProfilePicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilePictureServiceImpl implements ProfilePictureService {

	@Autowired
	private ProfilePictureDAO profilePictureDAO;
	
	@Override
	public void uploadProfilePicture(ProfilePicture profilePicture) {
		profilePictureDAO.uploadProfilePic(profilePicture);
	}

	@Override
	public ProfilePicture getProfilePicture(String username) {
		
		return profilePictureDAO.getProfilePic(username);
	}

}
