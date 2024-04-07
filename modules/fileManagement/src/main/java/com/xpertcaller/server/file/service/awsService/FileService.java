package com.xpertcaller.server.file.service.awsService;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.file.beans.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    byte[] downloadFile(String fileName) throws BusinessException, IOException;

    FileResponse uploadFiles(List<MultipartFile> multipartFiles) throws BusinessException, IOException;
}
