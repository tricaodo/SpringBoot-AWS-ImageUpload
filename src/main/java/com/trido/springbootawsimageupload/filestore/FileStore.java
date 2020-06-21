package com.trido.springbootawsimageupload.filestore;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;

@Service
public class FileStore {

    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String path, String fileName, Map<String, String> optionMetadata, InputStream inputStream) {

        ObjectMetadata metadata = new ObjectMetadata();
        optionMetadata.forEach(metadata::addUserMetadata);
        PutObjectRequest request = new PutObjectRequest(path, fileName, inputStream, metadata);
        s3.putObject(request);

    }

}
