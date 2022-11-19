package com.ffpo.mail.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ffpo.mail.mapper.MailMapper;
import com.ffpo.mail.service.MailService;
import com.ffpo.mail.service.ShippingMailService;
import com.huahuo.common.constants.UserConstants;
import com.huahuo.feign.FriendFeignService;
import com.huahuo.feign.MailFeignService;
import com.huahuo.feign.StampFeignService;
import com.huahuo.feign.UserFeignService;
import com.huahuo.model.common.dtos.PageResponseResult;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.friend.dtos.FriendIDto;
import com.huahuo.model.mail.dtos.*;
import com.huahuo.model.mail.pojos.Mail;
import com.huahuo.model.mail.pojos.ShippingMail;
import com.huahuo.model.user.pojos.User;
import com.huahuo.utils.common.GPSUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * @author Administrator
 * @description 针对表【mail】的数据库操作Service实现
 * @createDate 2022-10-27 22:06:32
 */
@Transactional
@Service
@Slf4j
public class MailServiceImpl extends ServiceImpl<MailMapper, Mail>
        implements MailService {
    @Autowired
    private StampFeignService stampFeignService;
    @Autowired
    private FriendFeignService friendFeignService;
    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private ShippingMailService shippingMailService;

    @Autowired
    private MailFeignService mailFeignService;
    @Autowired
    private RestHighLevelClient restHighLevelClient;


    /**
     * 生成新草稿
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult upload(MailDto dto) {
        Mail mail = new Mail();
        mail.setMsg(dto.getMsg());
        mail.setTitle(dto.getTitle());
        mail.setCreteTime(DateUtil.now());
        mail.setSendUserId(dto.getUserId());
        mail.setStampId(dto.getStampId());
        mail.setType(2);
        mail.setIsDelete(0);
        String img = stampFeignService.getStampImg(dto.getStampId());
        mail.setStampImg(img);
        save(mail);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(), "新草稿生成成功！");
    }

    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult list(MailPageDto dto) {
        dto.checkParam();
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<Mail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Mail::getUserId, dto.getUserId())
                .eq(Mail::getType, dto.getBagType())
                .eq(Mail::getIsDelete, 0)
                .orderByDesc(Mail::getCreteTime);
        if (dto.getBagType() == 0) {
            queryWrapper.eq(Mail::getIsSend, 1);
        }
        IPage pageResult = page(page, queryWrapper);
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(pageResult.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult senMailRandom(Mail mail) throws IOException {
        if (mail.getStampId() == 8) {
            //百分之五十的概率，没送出。
            Random random = new Random();
            double v = random.nextDouble();
            //送出失败
            if (v < 0.5) {
                //0-10
                int i = random.nextInt(5);
                String msg = UserConstants.MAIL_SEND_FAILED_MSG[i];
                return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS.getCode(), msg);

            }
        }
        //随机抽一个幸运用户出来获取邮件
        Integer id = userFeignService.getRandomUserId();
        ArrayList<String> getList = userFeignService.getGPS(id);

        String longitude2S = getList.get(0);
        Double longitude2 = new Double(longitude2S);

        String latitude2S = getList.get(1);
        Double latitude2 = new Double(latitude2S);

        Integer sendUserId = mail.getSendUserId();
        ArrayList<String> getListSend = userFeignService.getGPS(sendUserId);

        String longitude1S = getListSend.get(0);
        Double longitude1 = new Double(longitude1S);

        String latitude1S = getListSend.get(1);
        Double latitude1 = new Double(latitude1S);

        mail.setGetUserId(id);
        Double distance = GPSUtils.GetDistance(longitude1, latitude1, longitude2, latitude2);
        log.info("INSTANCE=------------------>" + distance + "km");
        Double minDouble = distance * 1.728;
        //送达需要的时间
        int min = minDouble.intValue();
        log.info("Time=------------------>" + min + "min");
        String now = DateUtil.now();
        Date date = DateUtil.parse(now);
        //获取送达时间
        Date dateTime = DateUtil.offsetMinute(date, min);
        String formatDateTime = DateUtil.formatDateTime(dateTime);
        log.info("现在时间==" + DateUtil.now());
        log.info("送达时间==" + dateTime);
        //送出位置
        mail.setSendTime(formatDateTime);
        //送达位置
        mail.setCreteTime(DateUtil.now());
        mail.setType(1);
        String stampImg = stampFeignService.getStampImgAndUpdateLife(mail.getStampId());
        mail.setStampImg(stampImg);
        mail.setUserId(mail.getSendUserId());
        mail.setSendAdd(userFeignService.getById(sendUserId).getAddress());
        mail.setGetAdd(userFeignService.getById(id).getAddress());


        Map resultMap = new HashMap(3);
        resultMap.put("code", AppHttpCodeEnum.SUCCESS.getCode());
        resultMap.put("sendTime", formatDateTime);
        getStamp(mail);
        User user = userFeignService.getById(sendUserId);
        mail.setSendUserName(user.getUsername());
        save(mail);
        log.info("mail id==" + mail.getId());
        log.info(mail.toString());
        //发邮件邮件 奖励100金币
        user.setCoinNum(user.getCoinNum() + 100);
        FriendIDto friendIDto = new FriendIDto();
        log.info("====1=====");
        friendIDto.setIdOne(mail.getSendUserId());
        log.info("====2===== " + mail.getSendUserId());
        friendIDto.setIdTwo(mail.getGetUserId());
        log.info("====3===== " + mail.getGetUserId());
        friendFeignService.becomeFriend(friendIDto);
        log.info("====4=====");
        if(mail.getIsPublic()==1)
        {
           user.setCoinNum(user.getCoinNum()+100);
           //因为公开邮件 奖励100金币
        }
        userFeignService.save(user);
        return ResponseResult.okResult(resultMap);
    }

    @Override
    public ResponseResult senMailById(Mail mail) throws IOException {
        if (mail.getStampId() == 8) {
            //百分之五十的概率，没送出。
            Random random = new Random();
            double v = random.nextDouble();
            //送出失败
            if (v < 0.5) {
                //0-10
                int i = random.nextInt(5);
                String msg = UserConstants.MAIL_SEND_FAILED_MSG[i];
                mail.setType(2);
                save(mail);
                return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS.getCode(), msg + "，信件先存入草稿了哦");

            }
        }
        //收到的id
        Integer id = mail.getGetUserId();
        ArrayList<String> getList = userFeignService.getGPS(id);

        String longitude2S = getList.get(0);
        Double longitude2 = new Double(longitude2S);

        String latitude2S = getList.get(1);
        Double latitude2 = new Double(latitude2S);

        //送出的用户
        Integer sendUserId = mail.getSendUserId();
        ArrayList<String> getListSend = userFeignService.getGPS(sendUserId);

        String longitude1S = getListSend.get(0);
        Double longitude1 = new Double(longitude1S);

        String latitude1S = getListSend.get(1);
        Double latitude1 = new Double(latitude1S);


        Double distance = GPSUtils.GetDistance(longitude1, latitude1, longitude2, latitude2);
        log.info("INSTANCE=------------------>" + distance + "km");
        Double minDouble = distance * 1.728;
        //送达需要的时间
        int min = minDouble.intValue();
        log.info("Time=------------------>" + min + "min");
        String now = DateUtil.now();
        Date date = DateUtil.parse(now);
        //获取送达时间
        Date dateTime = DateUtil.offsetMinute(date, min);
        String formatDateTime = DateUtil.formatDateTime(dateTime);
        log.info("现在时间==" + DateUtil.now());
        log.info("送达时间==" + dateTime);
        //送出位置
        mail.setSendTime(formatDateTime);
        //送达位置
        mail.setCreteTime(DateUtil.now());
        mail.setType(1);
        String stampImg = stampFeignService.getStampImgAndUpdateLife(mail.getStampId());
        mail.setStampImg(stampImg);
        mail.setUserId(mail.getSendUserId());
        User user = userFeignService.getById(sendUserId);
        mail.setSendAdd(user.getAddress());
        mail.setGetAdd(userFeignService.getById(id).getAddress());
        mail.setSendUserName(user.getUsername());
        save(mail);
        log.info("mail id==" + mail.getId());
        log.info(mail.toString());
        Map resultMap = new HashMap(3);
        resultMap.put("code", AppHttpCodeEnum.SUCCESS.getCode());
        resultMap.put("sendTime", formatDateTime);
        getStamp(mail);
        user.setCoinNum(user.getCoinNum() + 100);
        userFeignService.save(user);
        FriendIDto friendIDto = new FriendIDto();
        friendIDto.setIdOne(mail.getSendUserId());
        friendIDto.setIdTwo(mail.getGetUserId());
        friendFeignService.becomeFriend(friendIDto);
        return ResponseResult.okResult(resultMap);
    }

    @Override
    public ResponseResult search(EsSearchDto dto) throws IOException {
        //1.检查参数
        if (dto == null || StringUtils.isBlank(dto.getSearchWords())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.设置查询条件
        SearchRequest searchRequest = new SearchRequest("app_info_article");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //关键字的分词之后查询
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(dto.getSearchWords()).field("title").field("msg").field("tags").defaultOperator(Operator.OR);
        boolQueryBuilder.must(queryStringQueryBuilder);


        //分页查询
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(dto.getSize());


        //设置高亮  title
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.preTags("<font style='color: red; font-size: inherit;'>");
        highlightBuilder.postTags("</font>");
        searchSourceBuilder.highlighter(highlightBuilder);


        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);


        //3.结果封装返回

        List<Map> list = new ArrayList<>();

        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            Map map = JSON.parseObject(json, Map.class);
            //处理高亮
            if (hit.getHighlightFields() != null && hit.getHighlightFields().size() > 0) {
                Text[] titles = hit.getHighlightFields().get("title").getFragments();
                String title = StringUtils.join(titles);
                //高亮标题
                map.put("h_title", title);
            } else {
                //原始标题
                map.put("h_title", map.get("title"));
            }
            list.add(map);
        }

        return ResponseResult.okResult(list);

    }


    @Override
    public void getStamp(Mail mail) {
        Mail getMail;
        getMail = ObjectUtil.clone(mail);
        getMail.setType(0);
        getMail.setId(null);
        getMail.setUserId(mail.getUserId());
        getMail.setIsPublic(3);
        save(getMail);
        ShippingMail shippingMail = new ShippingMail();
        shippingMail.setIsSend(0);
        shippingMail.setSendTime(mail.getSendTime());
        shippingMail.setGetId(getMail.getId());
        shippingMail.setSendId(mail.getId());
        shippingMailService.save(shippingMail);

    }

    @Override
    public ResponseResult listPublicMails(PbMail dto) {
        dto.checkParam();
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<Mail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Mail::getIsPublic, 1)
                .eq(Mail::getIsSend, 1);
        if (dto.getOrderWay() == 1) {
            queryWrapper.orderByDesc(Mail::getSendTime);
        } else if (dto.getOrderWay() == 2) {
            queryWrapper.orderByDesc(Mail::getLikeNum);
        }
        IPage pageResult = page(page, queryWrapper);
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(pageResult.getRecords());
        return responseResult;
    }
}




