package com.trido.springbootawsimageupload.profile;

import java.util.UUID;

public class UserProfile {
    private UUID id;
    private String name;

    public UserProfile(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
