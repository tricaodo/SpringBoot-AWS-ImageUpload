package com.trido.springbootawsimageupload.controllers;

import com.trido.springbootawsimageupload.profile.UserProfile;
import com.trido.springbootawsimageupload.profile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/amazon")
@CrossOrigin(origins = "http://localhost:3000")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping
    public List<UserProfile> getUserProfiles(){
        return userProfileService.getUserProfiles();
    }

    @PostMapping(
            path = "/{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void uploadImage(@PathVariable("userProfileId") UUID userProfileId, @RequestParam("file") MultipartFile file) {
        userProfileService.save(userProfileId, file);
    }
}
