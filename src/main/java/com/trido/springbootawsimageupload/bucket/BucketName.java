package com.trido.springbootawsimageupload.bucket;

public enum BucketName {
    PROFILE_IMAGE("trido-image-upload");
    private String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
