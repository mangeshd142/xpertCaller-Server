package com.xpertcaller.server.aws.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;

@Component
public class S3BucketService {

    @Value("${aws.accessKey}")
    private String accessKey = "AKIAXJYJQ2SWAPOYJJEQ";

    @Value("${aws.secretKey}")
    private String secretKey = "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";

    @Value("${aws.region}")
    private String region = "ap-south-1";

    @Value("${aws.bucketName}")
    private String bucketName = "xpertcaller-service-v1";

    private final S3Client s3Client;

    public S3BucketService() {
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
                .build();
    }

    public void uploadFile(String key, File file) {
        s3Client.putObject(PutObjectRequest.builder()
                .bucket(this.bucketName)
                .key(key)
                .build(), file.toPath());
    }

    public void downloadFile(String key, File outputFile) {
        s3Client.getObject(GetObjectRequest.builder()
                .bucket(this.bucketName)
                .key(key)
                .build(), outputFile.toPath());
    }

    // Add other S3 operations as needed
}

