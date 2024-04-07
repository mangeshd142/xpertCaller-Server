package com.xpertcaller.server.file.serviceimpl;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.file.beans.FileResponse;
import com.xpertcaller.server.file.bo.FileBo;
import com.xpertcaller.server.file.service.awsService.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileBo fileBo;
    public byte[] downloadFile(String fileName) throws BusinessException, IOException {
        return fileBo.downloadFile(fileName);
    }

    @Override
    public FileResponse uploadFiles(List<MultipartFile> multipartFiles) throws BusinessException, IOException {
        return fileBo.uploadFiles(multipartFiles);
    }
}
