package com.ffpo.mail.controller;

import com.alibaba.fastjson.JSON;
import com.ffpo.mail.mapper.MailMapper;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.mail.dtos.EsMailDto;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @作者 花火
 * @创建日期 2022/11/15 10:05
 */
@RestController
@RequestMapping("/feign")
public class MailFeignController {

    @Autowired
    MailMapper mapper;
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @PostMapping("/es/upload")
    public ResponseResult add(@RequestBody EsMailDto esMailDto) throws IOException {
        BulkRequest bulkRequest = new BulkRequest("app_info_article");
        IndexRequest indexRequest = new IndexRequest().id(esMailDto.getId().toString())
                .source(JSON.toJSONString(esMailDto), XContentType.JSON);
        bulkRequest.add(indexRequest);
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return ResponseResult.okResult("添加成功");
    }


    @PostMapping("/es/upload/all")
    public ResponseResult add() throws IOException {
        List<EsMailDto> esMailDtos = mapper.esList();
        BulkRequest bulkRequest = new BulkRequest("app_info_article");
        for (EsMailDto esMailDto : esMailDtos) {
            IndexRequest indexRequest = new IndexRequest().id(esMailDto.getId().toString())
                    .source(JSON.toJSONString(esMailDto), XContentType.JSON);
            bulkRequest.add(indexRequest);
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        }

        return ResponseResult.okResult("添加成功");

    }
}
