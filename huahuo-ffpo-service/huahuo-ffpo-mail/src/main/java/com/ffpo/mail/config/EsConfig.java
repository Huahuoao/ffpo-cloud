package com.ffpo.mail.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @作者 花火
 * @创建日期 2022/11/15 9:18
 */
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class EsConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("43.143.160.178", 9200, "http")));
        return client;
    }
}
