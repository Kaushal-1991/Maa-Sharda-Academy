package com.academy.entity;

import java.time.LocalDateTime;

import com.academy.dto.StudentDto;
import com.academy.enums.MusicOption;
import com.academy.enums.RegistrationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "students",
    indexes = {
        @Index(name = "idx_student_email", columnList = "email")
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "music_option")
    private MusicOption musicOption;
    
    @Column(columnDefinition = "TEXT")
    private String address;
    
    @Enumerated(EnumType.STRING)
    @Column(name="registration_status")
    private RegistrationStatus registrationStatus;
    
    private String registrationNumber;
    
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
    	LocalDateTime now = LocalDateTime.now();
    	createdDate = now;
    	updatedDate = now;
    }
    
    @PreUpdate
    protected void onUpdate() {
    	updatedDate = LocalDateTime.now();
    }
    
    public StudentDto toDto() {
    	return new StudentDto(id,name,email,phone,musicOption,address,registrationStatus,registrationNumber);
    }
}