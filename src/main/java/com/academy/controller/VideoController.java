package com.academy.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.academy.dto.VideoUploadRequest;
import com.academy.entity.Video;
import com.academy.enums.VideoType;
import com.academy.exceptions.AcademyException;
import com.academy.serviceImpl.VideoService;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "*")
public class VideoController {

	private final VideoService videoService;

	public VideoController(VideoService videoService) {
		this.videoService = videoService;
	}

	@PostMapping("/upload")
	public ResponseEntity<Video> uploadVideo(@RequestParam("file") MultipartFile file,
			@RequestParam("videoType") VideoType videoType) throws IOException {

		long maxSize = 30L * 1024 * 1024;

		if (file.isEmpty()) {
			throw new AcademyException("Please select file", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (file.getContentType() == null || !file.getContentType().startsWith("video/")) {
			throw new AcademyException("Only video files are allowed", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (file.getSize() > maxSize) {
			throw new AcademyException("Video size must not be greater than 30 MB", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		VideoUploadRequest request = new VideoUploadRequest();

		request.setVideoType(videoType);

		Video savedVideo = videoService.uploadVideo(file, request);

		return ResponseEntity.ok(savedVideo);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteVideo(@PathVariable Long id) throws IOException{
		videoService.deleteVideo(id);		
		return new ResponseEntity<>("Video deleted sucessfully !!!",HttpStatus.OK);
	}
	
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Video>> getVideosByType(
            @PathVariable VideoType type
    ) {

        return ResponseEntity.ok(
                videoService.getVideosByType(type)
        );
    }
	
//    @GetMapping
//    public ResponseEntity<List<Video>> getAllVideos() {
//
//        return ResponseEntity.ok(
//                videoService.getAllVideos()
//        );
//    }
//

}