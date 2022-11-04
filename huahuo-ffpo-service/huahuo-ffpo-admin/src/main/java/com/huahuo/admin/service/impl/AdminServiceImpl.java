package com.huahuo.admin.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.admin.mapper.AdminMapper;
import com.huahuo.admin.service.AdminService;
import com.huahuo.model.admin.dtos.AdminLoginDto;
import com.huahuo.model.admin.dtos.AdminSignDto;
import com.huahuo.model.admin.pojos.Admin;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;

/**
 * * @author Administrator
 * * @description 针对表【admin】的数据库操作Service实现
* @createDate 2022-11-03 11:37:04
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService {
    ArrayList<String> code = new ArrayList<>();
   ArrayList<String> init(){
       code.add("Ljc6Q");
       code.add("WXiZH");
       code.add("wCpJH");
       code.add("9NdmS");
       code.add("ddDAo");
       code.add("P2Nn8");
       code.add("gcZKL");
       return  code;
   }
    @Override
    public ResponseResult login(AdminLoginDto dto) {
        Admin wmUser = null;
        //2.查询用户
            wmUser = getOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getUsername, dto.getUsername()));

        if (wmUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //3.比对密码
        String pswd = dto.getPassword();
        pswd = DigestUtils.md5DigestAsHex((pswd).getBytes());
        if (pswd.equals(wmUser.getPassword())) {
            String now = DateUtil.now();
            //4.返回数据  jwt
            Map<String, Object> payload = new HashMap<String, Object>();
            //签发时间
            payload.put(JWTPayload.ISSUED_AT, now);
            //过期时间
            Date date = DateUtil.parse(now);
            Date newTime = DateUtil.offsetDay(date, 3);
            String formatDateTime = DateUtil.formatDateTime(newTime);
            payload.put("outtime", formatDateTime);
            //生效时间
            payload.put(JWTPayload.NOT_BEFORE, now);
            //载荷
            payload.put("createname", "cjh");
            payload.put("id", wmUser.getId());
            String key = "bycbug";
            String token = JWTUtil.createToken(payload, key.getBytes());
            updateById(wmUser);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("token", token);
            wmUser.setPassword("");
            map1.put("user", wmUser);
            return ResponseResult.okResult(map1);
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
    }

    @Override
    public ResponseResult sign(AdminSignDto dto) {
        Admin wmUser = getOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getUsername, dto.getUsername()));
        if (wmUser != null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST);
        }
        String password = dto.getPassword();
        String psw = DigestUtils.md5DigestAsHex((password).getBytes());
        Admin user = new Admin();
        user.setPassword(psw);
        user.setUsername(dto.getUsername());
        String code = dto.getCode();
        boolean b = false;
        ArrayList<String> init = init();
        for (String s : init) {
            if(code.equals(s))
                b=true;
        }
        if (b==true) {
            save(user);
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        } else return ResponseResult.errorResult(AppHttpCodeEnum.SIGN_INVALID, "验证错误！");
    }
}




