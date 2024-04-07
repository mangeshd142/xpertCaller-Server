package com.xpertcaller.server.file.service;

import java.io.File;
import java.io.IOException;

public interface S3BucketService {
    byte[] downloadFile(String imageName) throws IOException;
    void uploadFile(String key, File file);
}
