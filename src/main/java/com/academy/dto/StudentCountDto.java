package com.academy.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCountDto {
	private long totalStudent;
	private long totalGuitarStudent;
	private long totalHarmoniumStudent;
	private long totalClassicalMusicStudent;
	private long totalLightMusicStudent;
}
