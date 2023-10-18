package com.wanted.wantedpreonboardingbackend.controller;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.domain.User;
import com.wanted.wantedpreonboardingbackend.dto.JobApplicationRequestDto;
import com.wanted.wantedpreonboardingbackend.repository.JobPostingRepository;
import com.wanted.wantedpreonboardingbackend.repository.UserRepository;
import com.wanted.wantedpreonboardingbackend.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Controller
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;
    private final UserRepository userRepository;
    private final JobPostingRepository jobPostingRepository;

    @PostMapping("/applies")
    public ResponseEntity<Void> createJobApplication(JobApplicationRequestDto dto) {
        User user = userRepository.findById(dto.getJobPostingId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        JobPosting jobPosting = jobPostingRepository.findById(dto.getJobPostingId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용 공고입니다."));

        Long id = jobApplicationService.createJobApplication(dto.toEntity(jobPosting, user));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/applies/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
