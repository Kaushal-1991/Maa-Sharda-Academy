package com.academy.entity;

import java.time.LocalDateTime;

import com.academy.enums.VideoType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vidoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private VideoType videoType;

	@Column(nullable = false, length = 1000)
	private String videoUrl;

	@Column(nullable = false)
	private String cloudinaryPublicId;

	private String originalFileName;

	private String format;

	private Long fileSize;

	private LocalDateTime createdAt;

	@PrePersist
	public void onCreate() {
		createdAt = LocalDateTime.now();
	}
}
