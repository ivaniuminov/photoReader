package com.agileengine.FileStorage.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PhotoData {
    private String id;
    private String author;
    private String camera;
    private String tags;
    @JsonProperty("cropped_picture")
    private String croppedPicture;
    @JsonProperty("full_picture")
    private String fullPicture;
}
