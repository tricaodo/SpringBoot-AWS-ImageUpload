package com.trido.springbootawsimageupload.datastore;

import com.trido.springbootawsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static final List<UserProfile> userProfiles = new ArrayList<>();
    static{
        userProfiles.add(new UserProfile(UUID.randomUUID(), "john"));
        userProfiles.add(new UserProfile(UUID.randomUUID(), "bob"));
        userProfiles.add(new UserProfile(UUID.randomUUID(), "jenny"));
    }

    public List<UserProfile> getUserProfiles(){
        return userProfiles;
    }
}
