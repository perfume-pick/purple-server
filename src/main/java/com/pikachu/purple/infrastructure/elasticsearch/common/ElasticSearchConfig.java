package com.pikachu.purple.infrastructure.elasticsearch.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ElasticSearchConfig extends ElasticsearchConfiguration {

    @Value(value = "${spring.data.elasticsearch.host}")
    private String host;

    @Value(value = "${spring.data.elasticsearch.username}")
    private String username;

    @Value(value = "${spring.data.elasticsearch.password}")
    private String password;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
            .connectedTo(host)
            .withBasicAuth(username, password)
            .build();
    }
}
