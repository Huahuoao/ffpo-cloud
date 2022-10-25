package com.huahuo.stamp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.stamp.pojos.Stamp;
import com.huahuo.stamp.mapper.StampMapper;
import com.huahuo.stamp.service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.huahuo.stamp.service.QiniuService;


/**
 * @author Administrator
 * @description 针对表【stamp(邮票)】的数据库操作Service实现
 * @createDate 2022-10-25 10:47:20
 */
@Service
@Transactional
public class StampServiceImpl extends ServiceImpl<StampMapper, Stamp>
        implements StampService {
    @Autowired
    private QiniuService qiniuService;

    @Override
    public String uploadImg(MultipartFile file) {
        return qiniuService.saveImage(file);
    }

    @Override
    public ResponseResult uploadStamp(Stamp stamp) {
        save(stamp);
        return  ResponseResult.okResult(stamp);
    }
}




