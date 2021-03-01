package com.agileengine.filestorage.service;

import com.agileengine.filestorage.domain.PhotoData;
import com.agileengine.filestorage.domain.PhotoFeed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PhotoDataRestService {
    @Value("${photo-source.baseUrl}")
    private String baseUrl;

    private final RestTemplate template;
    private final AuthTokenService authTokenService;


    public PhotoFeed readFeed() {
        return readPhotoPageIntenal("");
    }

    public PhotoFeed readPhotoPage(Integer id) {
        return readPhotoPageIntenal("?page=" + id);
    }

    public PhotoData readPhotoData(String id) {
        try {
            return getGetImageById(id);
        } catch (RestClientException e) {
            authTokenService.updateToken();
            return getGetImageById(id);
        }
    }

    private PhotoFeed readPhotoPageIntenal(String id) {
        try {
            return getPhotoPageById(id);
        } catch (RestClientException e) {
            authTokenService.updateToken();
            return getPhotoPageById(id);
        }
    }

    private PhotoData getGetImageById(String id) {
        return template.exchange(baseUrl + "/images/" + id, HttpMethod.GET,
                                 new HttpEntity<>(buildHttpHeaders()), PhotoData.class)
                       .getBody();
    }

    private PhotoFeed getPhotoPageById(String id) {
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
