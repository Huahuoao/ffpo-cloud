package com.huahua.user.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahua.user.mapper.UserMapper;
import com.huahua.user.service.UserService;
import com.huahuo.common.constants.UserConstants;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.user.dtos.UserLoginDto;
import com.huahuo.model.user.dtos.UserSignDto;
import com.huahuo.model.user.pojos.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @作者 花火
 * @创建日期 2022/10/23 15:44
 */
@Service
@Transactional
public class UserServiceImpl  extends ServiceImpl<UserMapper, User>  implements UserService {

    @Override
    public ResponseResult login(UserLoginDto dto) {
        //1.检查参数
        if (StringUtils.isBlank(dto.getUsername()) || StringUtils.isBlank(dto.getPassword())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "用户名或密码为空");
        }

        //2.查询用户
        User wmUser = getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, dto.getUsername()));
        if (wmUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

        //3.比对密码
        String salt = wmUser.getSalt();
        String pswd = dto.getPassword();
        pswd = DigestUtils.md5DigestAsHex((pswd + salt).getBytes());
        if (pswd.equals(wmUser.getPassword())) {
            //4.返回数据  jwt
            DateTime now = DateTime.now();
            DateTime newTime = now.offsetNew(DateField.MINUTE, 360);

            Map<String,Object> payload = new HashMap<String,Object>();
            //签发时间
            payload.put(JWTPayload.ISSUED_AT, now);
            //过期时间
            payload.put(JWTPayload.EXPIRES_AT, newTime);
            //生效时间
            payload.put(JWTPayload.NOT_BEFORE, now);
            //载荷
            payload.put("createname", "cjh");
            payload.put("id", wmUser.getId());

            String key = "bycbug";
            String token = JWTUtil.createToken(payload, key.getBytes());








            Map<String, Object> map1 = new HashMap<>();
            map1.put("token", token);
            wmUser.setSalt("");
            wmUser.setPassword("");
            map1.put("user", wmUser);
            return ResponseResult.okResult(map1);

        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
    }

    @Override
    public ResponseResult sign(UserSignDto dto) {
        if (StringUtils.isBlank(dto.getUsername()) || StringUtils.isBlank(dto.getPassword())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "用户名或密码为空");
        }
        User wmUser = getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, dto.getUsername()));
        if (wmUser != null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST);
        }
        String salt = UserConstants.USER_LOGIN_SALT;
        String password = dto.getPassword();
        String psw = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        User user = new User();
        user.setSalt(salt);
        user.setPassword(psw);
        user.setUsername(dto.getUsername());
        user.setCreteTime(LocalDateTime.now());
        save(user);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
