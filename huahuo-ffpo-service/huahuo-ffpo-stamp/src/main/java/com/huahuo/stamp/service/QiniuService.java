package com.huahuo.stamp.service;

import org.springframework.web.multipart.MultipartFile;

public interface QiniuService {
    public String saveImage(MultipartFile file);
    public void deleteImg(String fileKey);
    public String getUpToken();
}
