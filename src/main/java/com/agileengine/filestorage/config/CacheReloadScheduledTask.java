package com.agileengine.filestorage.config;

import com.agileengine.filestorage.domain.PhotoDataCache;
import com.agileengine.filestorage.service.PhotoDataReadingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheReloadScheduledTask {
    private final PhotoDataReadingService photoDataReadingService;
    private final PhotoDataCache cache;

    @Scheduled(fixedDelayString = "${reload-task.fixed-delay:3600000}")
    public void reloadCache() {
        log.info("Cache loading started");
        var readData = photoDataReadingService.readPhotoData();
        log.info("Cache loading finished");
        cache.setPhotoDataList(readData);
    }
}
