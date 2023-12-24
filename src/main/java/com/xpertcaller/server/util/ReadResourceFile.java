package com.xpertcaller.server.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class ReadResourceFile {
    public String readFileFromResources(String fileName) throws IOException {
        // Specify the file name or path within the resources directory

        // Create a ClassPathResource using the specified file name
        Resource resource = new ClassPathResource(fileName);

        // Open an InputStream from the resource
        try (InputStream inputStream = resource.getInputStream()) {
            // Read the contents of the file into a byte array
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);

            // Convert the byte array to a String (assuming the file contains text)
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }
}
