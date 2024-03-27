package com.xpertcaller.server.aws.service.interfaces;

import java.io.File;

public interface S3BucketServiceInterface {
    void downloadFile(String key, File outputFile);
    void uploadFile(String key, File file);
}
