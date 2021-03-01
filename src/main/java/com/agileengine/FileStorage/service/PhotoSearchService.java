package com.agileengine.FileStorage.service;

import com.agileengine.FileStorage.domain.PhotoData;
import com.agileengine.FileStorage.domain.PhotoDataCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoSearchService {
    private final PhotoDataCache cache;

    public List<PhotoData> searchBySearchTerm(String searchTerm) {
        var list = cache.getPhotoDataList();
        return list.stream()
                       .filter(e -> filterBySearchTerm(e, searchTerm))
                       .collect(Collectors.toList());
    }

    private boolean filterBySearchTerm(PhotoData data, String searchTerm) {
        return data.getAuthor().contains(searchTerm)
                || data.getCamera().contains(searchTerm)
                || data.getTags().contains(searchTerm);
    }
}