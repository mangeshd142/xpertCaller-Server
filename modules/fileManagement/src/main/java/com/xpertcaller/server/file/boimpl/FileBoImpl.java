package com.xpertcaller.server.file.boimpl;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.common.util.CommonUtil;
import com.xpertcaller.server.file.beans.FileResponse;
import com.xpertcaller.server.file.bo.FileBo;
import com.xpertcaller.server.file.service.S3BucketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileBoImpl implements FileBo {
    @Autowired
    S3BucketService s3BucketService;

    private static final Logger logger = LoggerFactory.getLogger(FileBoImpl.class);

    @Override
    public  byte[] downloadFile(String fileName) throws BusinessException, IOException {
        return s3BucketService.downloadFile(fileName);
    }

    @Override
    public FileResponse uploadFiles(List<MultipartFile> multipartFiles) throws BusinessException, IOException {
        List<String> profilePicIds = new ArrayList<>();
        for (MultipartFile multipartFile: multipartFiles) {
            if (!multipartFile.isEmpty()) {
                String fullFileName = multipartFile.getOriginalFilename();
                String fileName = CommonUtil.getFileName(fullFileName);
                String fileFormat = CommonUtil.getFileFormat(fullFileName);
                File file = File.createTempFile("temp", null);
                multipartFile.transferTo(file);
                String profilePicId = fileName + "_" +UUID.randomUUID().toString() + "." + fileFormat;
                s3BucketService.uploadFile(profilePicId, file);
                profilePicIds.add(profilePicId);
            } else {
                BusinessException businessException = new BusinessException("One of the file is empty");
                logger.error("One of the file is empty", businessException);
                throw businessException;
            }
        }

        return new FileResponse(profilePicIds);
    }
}
