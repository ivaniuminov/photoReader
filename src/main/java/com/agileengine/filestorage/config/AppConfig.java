package com.agileengine.filestorage.config;

import com.agileengine.filestorage.domain.PhotoDataCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Value("${http-client.connection-timeout:10000}")
    private int connectionTimeout;
    @Value("${http-client.read-timeout:10000}")
    private int readTimeout;

    @Bean
    public RestTemplate template() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    @Bean
    public PhotoDataCache cache() {
        return new PhotoDataCache();
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }
}
