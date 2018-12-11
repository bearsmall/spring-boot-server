package com.xy.springbootvue.web;

import com.xy.springbootvue.util.ZipUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {

    @Value("${prop.upload-zip-folder}")
    private String UPLOAD_ZIP_FOLDER;
    @Value("${prop.upload-unzip-folder}")
    private String UPLOAD_UNZIP_FOLDER;
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
            //TODO
            //待优化
            byte[] bytes = file.getBytes();

            //组织文件上传、解压目录及路径（采用的目录结构为：年/月/日/文件 的形式
            Calendar now = Calendar.getInstance();
            Date date = new Date(now.getTimeInMillis());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH) + 1;
            int day = now.get(Calendar.DAY_OF_MONTH);
            String fileDir = year+File.separator+month+File.separator+day;
            String storeFileName = simpleDateFormat.format(date)+"-"+file.getOriginalFilename();

            //构造上传地址、压缩地址
            String zipPath = UPLOAD_ZIP_FOLDER + File.separator+fileDir+File.separator+storeFileName;
            String unZipPath = UPLOAD_UNZIP_FOLDER + File.separator+fileDir+File.separator+storeFileName.substring(0,storeFileName.indexOf("."));

            //目录构造
            Path path = Paths.get(zipPath);
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_ZIP_FOLDER + File.separator+fileDir));
            }
            //写入磁盘
            Files.write(path, bytes);
            //如果上传压缩包则进行解压
            if(storeFileName.endsWith("zip")) {
                ZipUtils.decompress(zipPath, unZipPath);
            }
            code = "file upload success";
            logger.debug(code);
        } catch (IOException e) {
            code = e.getMessage();
            logger.error(code);
        } catch (Exception e) {
            code = e.getMessage();
            logger.error(code);
        }
        return code;
    }
}