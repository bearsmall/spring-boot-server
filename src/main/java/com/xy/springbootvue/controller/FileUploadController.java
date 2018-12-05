package com.xy.springbootvue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
    private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/singlefile")
    public Object singleFileUpload(MultipartFile file) {
        String code = null;
        if (Objects.isNull(file) || file.isEmpty()) {
            code = "empty file";
            logger.error(code);
            return code;
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            Files.write(path, bytes);
            code = "file upload success";
            logger.debug(code);
            return code;
        } catch (IOException e) {
            code = e.getMessage();
            logger.error(code);
            return code;
        }
    }
}
