package com.xpertcaller.server.util;

import com.xpertcaller.server.beans.user.User;
import com.xpertcaller.server.exception.userdefined.BusinessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CommonUtil {
    private CommonUtil(){}
    public static User getCurrentUser() throws BusinessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }else {
            throw new BusinessException("No user authenticated");
        }
    }

    public static String getFileFormat(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex == -1 || lastIndex == fileName.length() - 1) {
            return ""; // No file extension found or the dot is at the end
        } else {
            return fileName.substring(lastIndex + 1);
        }
    }
}
