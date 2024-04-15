package com.xpertcaller.server.file.controller;

import com.xpertcaller.server.common.exception.BusinessException;
import com.xpertcaller.server.file.beans.FileResponse;
import com.xpertcaller.server.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @CrossOrigin(origins = "*")
    @RequestMapping("/getFile/{fileName}")
    public ResponseEntity getFile(@PathVariable String fileName) throws IOException, BusinessException {
        byte[] imageContent = fileService.downloadFile(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageContent.length);
        headers.setContentDispositionFormData("attachment", fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(new ByteArrayInputStream(imageContent)));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/uploadFiles")
    public FileResponse uploadFiles(@RequestParam(name = "files") List<MultipartFile> multipartFiles) throws IOException, BusinessException {
        return fileService.uploadFiles(multipartFiles);
    }
}
