package com.xpertcaller.server.common.util;

public class CommonUtil {
    private CommonUtil(){}

    public static String getFileFormat(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex == -1 || lastIndex == fileName.length() - 1) {
            return ""; // No file extension found or the dot is at the end
        } else {
            return fileName.substring(lastIndex + 1);
        }
    }
    public static String getFileName(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex == -1 || lastIndex == fileName.length() - 1) {
            return fileName; // No file extension found or the dot is at the end
        } else {
            return fileName.substring(0, lastIndex);
        }
    }
}
