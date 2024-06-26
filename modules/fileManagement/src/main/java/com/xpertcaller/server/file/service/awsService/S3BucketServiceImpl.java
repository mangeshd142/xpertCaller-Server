package com.xpertcaller.server.file.service.awsService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;

@Component
public class S3BucketServiceImpl implements S3BucketService {

    private String accessKey;

    private String secretKey;

    private String region;
    private String bucketName;

    private final S3Client s3Client;

    public S3BucketServiceImpl(@Value("${aws.accessKey}") String accessKey,
                               @Value("${aws.secretKey}") String secretKey,
                               @Value("${aws.region}") String region,
                               @Value("${aws.bucketName}") String bucketName) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.region = region;
        this.bucketName = bucketName;

        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
                .build();
    }

    @Override
    public void uploadFile(String key, File file) {
        s3Client.putObject(PutObjectRequest.builder()
                .bucket(this.bucketName)
                .key(key)
                .build(), file.toPath());
    }

    @Override
    public byte[] downloadFile(String imageName) throws IOException {
        return s3Client.getObject(GetObjectRequest.builder()
                .bucket(this.bucketName)
                .key(imageName)
                .build()).readAllBytes();
    }

    @Override
    public void deleteFile(String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(this.bucketName)
                .key(key)
                .build());
    }

}

