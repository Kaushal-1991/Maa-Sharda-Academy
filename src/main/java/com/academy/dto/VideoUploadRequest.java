package com.academy.dto;

import com.academy.enums.VideoType;

import lombok.Data;

@Data
public class VideoUploadRequest {
    private VideoType videoType;
}