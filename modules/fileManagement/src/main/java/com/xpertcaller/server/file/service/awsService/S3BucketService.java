package com.xpertcaller.server.file.service.awsService;

import java.io.File;
import java.io.IOException;

public interface S3BucketService {
    byte[] downloadFile(String imageName) throws IOException;
    void uploadFile(String key, File file);

    void deleteFile(String key);
}
