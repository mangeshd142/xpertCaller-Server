package com.xpertcaller.server.aws.service.interfaces;

import java.io.File;

public interface S3BucketService {
    void downloadFile(String key, File outputFile);
    void uploadFile(String key, File file);
}
