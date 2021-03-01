package com.agileengine.FileStorage.service;

import com.agileengine.FileStorage.domain.PhotoData;
import com.agileengine.FileStorage.domain.PhotoFeed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoDataReadingService {
    private final PhotoDataRestService photoDataRestService;

    public List<PhotoData> readPhotoData() {
        List<PhotoData> result = new ArrayList<>();
        var photoFeed = photoDataRestService.readFeed();
        int pageCount = photoFeed.getPageCount();

        for (int currPage = 2; currPage <= pageCount; currPage++) {
            photoFeed
                    .getPictures()
                    .forEach(e -> result.add(photoDataRestService.readPhotoData(e.getId())));
            photoFeed = photoDataRestService.readPhotoPage(currPage);
        }

        return result;
    }
}
