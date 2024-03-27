package com.xpertcaller.server.aws.service;
import com.xpertcaller.server.aws.service.interfaces.S3BucketService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;

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
    public void downloadFile(String key, File outputFile) {
        s3Client.getObject(GetObjectRequest.builder()
                .bucket(this.bucketName)
                .key(key)
                .build(), outputFile.toPath());
    }

}

