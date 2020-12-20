package com.chen.human_resource_system.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Jack
 * @date 2019-07-28-18:58
 */
@Component
@Scope(scopeName = "singleton")
public class FileUploadUtil {

    @Value("${photo.dir}")
    public String uploadDir;

    public void uploadFiles(MultipartFile[] photos, String[] photoUploadNames){

        for (int i = 0; i< photoUploadNames.length; i++) {
            //判断文件夹是否存在
            String uploadDir = getUploadDir();
            File dir = new File(uploadDir);
            if(!dir.exists()){
                dir.mkdirs();
            }
            //上传文件
            File file = new File(uploadDir + photoUploadNames[i]);
            try {
                photos[i].transferTo(file);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public void uploadOneFile(MultipartFile photo, String photoUploadName){

            //判断文件夹是否存在
            String uploadDir = getUploadDir();
            File dir = new File(uploadDir);
            if(!dir.exists()){
                dir.mkdirs();
            }
            //上传文件

            File file = new File(uploadDir + photoUploadName);
            try {
                photo.transferTo(file);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
    }

    public String getNewFileName(MultipartFile memberIcon) {

        String str = UUID.randomUUID().toString().substring(0, 15).replace("-", "a");
        //获取原始文件名
        String originalFilename = memberIcon.getOriginalFilename();
        //获取后缀名
        int beginIndex = originalFilename.lastIndexOf(".");
        String photoType = originalFilename.substring(beginIndex);
        //新的文件名
        String photoName = str + photoType;
        return photoName;

    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void deletePhoto(String imgPath) {

        File file = new File(imgPath);
        file.delete();

    }

    public String[] getFileNewNames(MultipartFile[] photos){

        String[] photoLocs = new String[photos.length];
        for (int i = 0; i< photos.length; i++) {
            String str = UUID.randomUUID().toString().substring(0, 15).replace("-", "a");
            //获取原始文件名
            String originalFilename = photos[i].getOriginalFilename();
            //获取后缀名
            int beginIndex = originalFilename.lastIndexOf(".");
            String photoType = originalFilename.substring(beginIndex);
            //新的文件名
            String photoName = str + photoType;
            photoLocs[i] = photoName;
        }
        return photoLocs;
    }
}
