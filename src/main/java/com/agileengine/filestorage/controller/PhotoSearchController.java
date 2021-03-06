package com.agileengine.filestorage.controller;

import com.agileengine.filestorage.domain.PhotoData;
import com.agileengine.filestorage.service.PhotoSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhotoSearchController {
    private final PhotoSearchService searchService;

    @GetMapping(path = "/search/{searchTerm}")
    public ResponseEntity<List<PhotoData>> getPhotoDataBySearchTerm(@PathVariable("searchTerm") String searchTerm) {
        return new ResponseEntity<>(searchService.searchBySearchTerm(searchTerm),
                                    HttpStatus.OK);
    }
}
