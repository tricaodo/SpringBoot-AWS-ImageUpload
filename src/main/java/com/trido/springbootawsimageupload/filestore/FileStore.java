package com.trido.springbootawsimageupload.filestore;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.trido.springbootawsimageupload.bucket.BucketName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
public class FileStore {

    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save() {
        PutObjectRequest request = new PutObjectRequest(BucketName.PROFILE_IMAGE.getBucketName(), UUID.randomUUID().toString(), new File("src/main/java/com/trido/springbootawsimageupload/images/test.jpeg"));
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/jpeg");
        request.setMetadata(metadata);
        s3.putObject(request);
    }

}
