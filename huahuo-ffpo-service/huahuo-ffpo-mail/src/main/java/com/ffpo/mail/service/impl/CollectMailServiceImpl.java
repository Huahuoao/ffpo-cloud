package com.ffpo.mail.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ffpo.mail.mapper.CollectMailMapper;
import com.ffpo.mail.service.CollectMailService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.mail.pojos.CollectMail;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author huahuo
* @description 针对表【collect_mail】的数据库操作Service实现
* @createDate 2022-11-20 15:19:12
*/
@Service
public class CollectMailServiceImpl extends ServiceImpl<CollectMailMapper, CollectMail>
    implements CollectMailService {

    @Override
    public ResponseResult list(Integer id) {
      return null;
    }
}




