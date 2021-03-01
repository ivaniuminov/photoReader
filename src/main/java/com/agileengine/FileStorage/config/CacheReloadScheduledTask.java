package com.agileengine.FileStorage.config;

import com.agileengine.FileStorage.domain.PhotoDataCache;
import com.agileengine.FileStorage.service.PhotoDataReadingService;
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
