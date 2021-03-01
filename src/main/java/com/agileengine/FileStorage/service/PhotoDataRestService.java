package com.agileengine.FileStorage.service;

import com.agileengine.FileStorage.domain.PhotoData;
import com.agileengine.FileStorage.domain.PhotoFeed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PhotoDataRestService {
    @Value("${photo-source.baseUrl}")
    private String baseUrl;

    private final RestTemplate template;
    private final AuthTokenService authTokenService;


    public PhotoFeed readFeed() {
        return reedPhotoPageIntenal("");
    }

    public PhotoFeed readPhotoPage(Integer id) {
        return reedPhotoPageIntenal("?page=" + id);
    }

    public PhotoData readPhotoData(String id) {
        return template.exchange(baseUrl + "/images/" + id, HttpMethod.GET,
                                 new HttpEntity<>(buildHttpHeaders()), PhotoData.class)
                       .getBody();
    }

    private PhotoFeed reedPhotoPageIntenal(String id) {
        return template.exchange(baseUrl + "/images" + id, HttpMethod.GET,
                                 new HttpEntity<>(buildHttpHeaders()), PhotoFeed.class)
                       .getBody();
    }

    private HttpHeaders buildHttpHeaders() {
        var headers = new HttpHeaders();
        headers.setBearerAuth(authTokenService.retrieveToken());
        return headers;
    }
}
