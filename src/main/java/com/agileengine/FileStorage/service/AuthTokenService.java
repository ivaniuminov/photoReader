package com.agileengine.FileStorage.service;

import com.agileengine.FileStorage.domain.AuthRequest;
import com.agileengine.FileStorage.domain.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthTokenService {
    @Value("${photo-source.apiKey}")
    private String apiKey;
    @Value("${photo-source.baseUrl}")
    private String baseUrl;
    private String token;

    private final RestTemplate template;

    public String retrieveToken() {
        return token == null
                       ? token = fetchToken()
                       : token;
    }

    private String fetchToken() {
        return template.postForEntity(baseUrl + "/auth", new AuthRequest(apiKey), AuthResponse.class)
                       .getBody()
                       .getToken();
    }
}
