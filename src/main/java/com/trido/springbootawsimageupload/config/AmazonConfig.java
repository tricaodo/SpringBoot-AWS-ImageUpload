package com.trido.springbootawsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 s3() {
        Regions clientRegion = Regions.US_WEST_1;
        AWSCredentials basicAWSCredentials = new BasicAWSCredentials(
                "AKIAIFLAEAHPOTJUK4EQ",
                "a27zmkVa2r63Is7723WsQDKmSMcInlfUYpMjSnpy"
        );
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(clientRegion)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }
}
