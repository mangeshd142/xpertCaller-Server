package com.xpertcaller.server.controllers;


import com.xpertcaller.server.aws.service.interfaces.S3BucketServiceInterface;
import com.xpertcaller.server.beans.user.ProfileDetails;
import com.xpertcaller.server.beans.user.User;
import com.xpertcaller.server.exception.userdefined.BusinessException;
import com.xpertcaller.server.service.interfaces.UserService;
import com.xpertcaller.server.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    S3BucketServiceInterface s3BucketService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/addProfileDetails", method = RequestMethod.POST)
    public ProfileDetails addProfileDetails(@RequestBody ProfileDetails profileDetails) throws BusinessException {
        return userService.addProfileDetails(profileDetails);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/fetchCurrentUser", method = RequestMethod.GET)
    public User fetchCurrentUser() throws BusinessException {
        return userService.fetchCurrentUser();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user) throws BusinessException {
        return userService.updateUser(user);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/fetchProfileDetails", method = RequestMethod.GET)
    public ProfileDetails fetchProfileDetails() throws BusinessException {
        return userService.fetchProfileDetails();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/uploadProfilePic")
    public ProfileDetails uploadProfilePic(@RequestParam(name = "file") MultipartFile multipartFile) throws IOException, BusinessException {
        if(!multipartFile.isEmpty()) {
            String fileFormat = CommonUtil.getFileFormat(multipartFile.getOriginalFilename());
            File file = File.createTempFile("temp", null);
            multipartFile.transferTo(file);
            String profilePicId = UUID.randomUUID().toString() + "." + fileFormat;
            s3BucketService.uploadFile(profilePicId, file);
            return userService.updateProfilePictureId(profilePicId);
        }else{
            BusinessException businessException = new BusinessException("File is empty");
            logger.error("File is empty", businessException);
            throw businessException;
        }
    }
    @CrossOrigin(origins = "*")
    @RequestMapping("/uploadDocuments")
    public ProfileDetails uploadDocuments(@RequestParam(name = "files") List<MultipartFile> multipartFiles) throws IOException, BusinessException {
        List<String> profilePicIds = new ArrayList<>();
        for (MultipartFile multipartFile: multipartFiles) {
            if (!multipartFile.isEmpty()) {
                String fileFormat = CommonUtil.getFileFormat(multipartFile.getOriginalFilename());
                File file = File.createTempFile("temp", null);
                multipartFile.transferTo(file);
                String profilePicId = UUID.randomUUID().toString() + "." + fileFormat;
                s3BucketService.uploadFile(profilePicId, file);
                profilePicIds.add(profilePicId);
            } else {
                BusinessException businessException = new BusinessException("One of the file is empty");
                logger.error("One of the file is empty", businessException);
                throw businessException;
            }
        }
        return userService.updateDocumentIds(profilePicIds);
    }
}
