package com.huahuo.stamp.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.huahuo.utils.common.FileUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import jdk.internal.instrumentation.TypeMappings;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.huahuo.stamp.service.QiniuService;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class QiniuServiceImpl implements QiniuService {
    private static final Logger logger = LoggerFactory.getLogger(QiniuServiceImpl.class);

    // 设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "isGLzqxQMhq8JZBA7ln7KaKiTPIgMPNoDb6d2iqg";
    String SECRET_KEY = "Uq_ZqhSwNOaW7sbgKDYrWLSa3rpJGEbTNmCYzfH5";

    // 要上传的空间（创建空间的名称）
    String bucketname = "huahuoaoao";

    // 密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    // 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
    Configuration cfg = new Configuration(Zone.zone2());
    // ...其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);

    // 使用的是测试域名
    private static String QINIU_IMAGE_DOMAIN = "image.fzuhuahuo.cn/";

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    @Override
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }
    @Override
    public String saveImage(MultipartFile file) {
        try {
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
            // 判断是否是合法的文件后缀
            if (!FileUtil.isFileAllowed(fileExt)) {
                return null;
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            // 调用put方法上传
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
                return QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
            } else {

                return null;
            }
        } catch (QiniuException e) {
            // 请求失败时打印的异常的信息
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public void deleteImg(String fileKey)
    {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释

        String accessKey = ACCESS_KEY;
        String secretKey = SECRET_KEY;

        String bucket = bucketname;
        String key = fileKey;

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }

    }
}
