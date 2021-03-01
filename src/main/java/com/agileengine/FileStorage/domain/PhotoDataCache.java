package com.agileengine.FileStorage.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PhotoDataCache {
    List<PhotoData> photoDataList = new ArrayList<>();
}
