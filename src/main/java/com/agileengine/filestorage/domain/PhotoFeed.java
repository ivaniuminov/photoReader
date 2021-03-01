package com.agileengine.filestorage.domain;

import lombok.Data;

import java.util.List;

@Data
public class PhotoFeed {
    private List<PhotoData> pictures;
    private Integer page;
    private Integer pageCount;
    private Boolean hasMore;
}
