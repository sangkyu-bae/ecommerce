package org.example.infra.elasticsearch;


import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.net.ssl.SSLContext;
@Configuration
@EnableElasticsearchRepositories
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {


    @Value("${spring.data.elasticsearch.uris}")
    private String url;
    @Value("${spring.data.elasticsearch.username}")
    private String userName;

    @Value("${spring.data.elasticsearch.pw}")
    private String pw;
    @Override
    public RestHighLevelClient elasticsearchClient() {

        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo(url)
                .withBasicAuth(userName,pw)
                .build();
        return RestClients.create(configuration).rest();
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }

}
