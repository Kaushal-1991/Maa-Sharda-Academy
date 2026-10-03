package com.academy.reposistory;


import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.entity.Video;
import com.academy.enums.VideoType;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByVideoType(VideoType videoType);
}