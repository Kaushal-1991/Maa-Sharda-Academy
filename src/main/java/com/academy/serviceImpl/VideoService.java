package com.academy.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.academy.dto.VideoUploadRequest;
import com.academy.entity.Video;
import com.academy.enums.VideoType;
import com.academy.exceptions.AcademyException;
import com.academy.reposistory.VideoRepository;

@Service
public class VideoService {

	private final VideoRepository videoRepository;
	private final CloudinaryService cloudinaryService;

	public VideoService(VideoRepository videoRepository, CloudinaryService cloudinaryService) {
		this.videoRepository = videoRepository;
		this.cloudinaryService = cloudinaryService;
	}

	public Video uploadVideo(MultipartFile file, VideoUploadRequest request) throws IOException {

		Map<?, ?> result = cloudinaryService.uploadVideo(file);
		String videoUrl = result.get("secure_url").toString();

		String publicId = result.get("public_id").toString();

		String format = result.get("format").toString();

		Long fileSize = Long.valueOf(result.get("bytes").toString());

		Video video = Video.builder().videoType(request.getVideoType()).videoUrl(videoUrl).cloudinaryPublicId(publicId)
				.originalFileName(file.getOriginalFilename()).format(format).fileSize(fileSize).build();

		return videoRepository.save(video);
	}

	public void deleteVideo(Long id) throws IOException {

		Video video = videoRepository.findById(id)
				.orElseThrow(() -> new AcademyException("Video not found", HttpStatus.NOT_FOUND));

		cloudinaryService.deleteVideo(video.getCloudinaryPublicId());

		videoRepository.delete(video);
	}

	public List<Video> getVideosByType(VideoType type) {
		List<Video> videoList = videoRepository.findByVideoType(type);
        if(videoList.isEmpty()) {
        	throw new AcademyException("Video not found",HttpStatus.NOT_FOUND);
        }
		return videoList;
	}
}
