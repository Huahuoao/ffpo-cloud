package com.huahuo.utils.common;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.tea.*;
import com.aliyun.teaopenapi.models.Config;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;

public class AliSMS {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    private static String accessKeyId = "LTAI5tHhDvJrznrhCKJNfW42";
    private static String accessKeySecret = "oPXWpFdDivohjf6lybZLGjDCp2pS10";
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

      public static String sendSMS(String phone) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = AliSMS.createClient(accessKeyId, accessKeySecret);
        String randomCode = String.valueOf((int)((Math.random() * 9 + 1) * Math.pow(10,5)));
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setTemplateParam("{\"code\":\""+randomCode+"\"}");

        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();

        try {
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, runtime);
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
        return randomCode;
    }
}
