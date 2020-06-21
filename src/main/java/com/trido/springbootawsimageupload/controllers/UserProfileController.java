package com.trido.springbootawsimageupload.controllers;

import com.trido.springbootawsimageupload.filestore.FileStore;
import com.trido.springbootawsimageupload.profile.UserProfile;
import com.trido.springbootawsimageupload.profile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/amazon")
public class UserProfileController {

    @Autowired
    private FileStore fileStore;

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    public void uploadImage(){
        fileStore.save();
    }

    @GetMapping
    public List<UserProfile> getUserProfiles(){
        return userProfileService.getUserProfiles();
    }
}
