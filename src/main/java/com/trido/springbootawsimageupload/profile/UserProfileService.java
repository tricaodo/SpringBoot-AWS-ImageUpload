package com.trido.springbootawsimageupload.profile;

import com.trido.springbootawsimageupload.bucket.BucketName;
import com.trido.springbootawsimageupload.datastore.FakeUserProfileDataStore;
import com.trido.springbootawsimageupload.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.*;

@Service
public class UserProfileService {

    private final FakeUserProfileDataStore fakeUserProfileDataStore;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(FakeUserProfileDataStore fakeUserProfileDataStore, FileStore fileStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
        this.fileStore = fileStore;
    }

    public List<UserProfile> getUserProfiles(){
        return fakeUserProfileDataStore.getUserProfiles();
    }

    public void save(UUID userProfileId, MultipartFile file){
        if(file.isEmpty()){
            throw new IllegalStateException("File cannot be empty");
        }
        if(!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_GIF_VALUE, IMAGE_PNG_VALUE).contains(file.getContentType())){
            throw new IllegalStateException("File must have extension jpeg/gif/png");
        }

        UserProfile userProfile = fakeUserProfileDataStore
                .getUserProfiles()
                .stream()
                .filter(profile -> profile.getId().compareTo(userProfileId) == 0)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Couldn't find profile id %s in our store", userProfileId.toString())));

        Map<String,String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userProfile.getId());
        String fileName = String.format("%s-%s", file.getName(), UUID.randomUUID());

        try {
            fileStore.save(path, fileName, metadata, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
