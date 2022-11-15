package com.huahuo.feign;

import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.mail.dtos.EsMailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

/**
 * @作者 花火
 * @创建日期 2022/11/15 10:04
 */
@FeignClient(name="huahuo-ffpo-mail",path = "/feign")
@Component
public interface MailFeignService {
    @PostMapping("/es/upload")
    public ResponseResult add(@RequestBody EsMailDto esMailDto) throws IOException;

}
