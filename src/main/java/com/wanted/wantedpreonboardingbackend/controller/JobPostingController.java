package com.wanted.wantedpreonboardingbackend.controller;

import com.wanted.wantedpreonboardingbackend.domain.CompanyUser;
import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.domain.User;
import com.wanted.wantedpreonboardingbackend.dto.JobPostingListResponseDto;
import com.wanted.wantedpreonboardingbackend.dto.JobPostingRequestDto;
import com.wanted.wantedpreonboardingbackend.dto.JobPostingResponseDto;
import com.wanted.wantedpreonboardingbackend.dto.JopPostingUpdateRequestDto;
import com.wanted.wantedpreonboardingbackend.repository.UserRepository;
import com.wanted.wantedpreonboardingbackend.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class JobPostingController {

    private final JobPostingService jobPostingService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Long> createJobPosting(JobPostingRequestDto dto) {
        User foundUser = userRepository.findByName(dto.getCompanyName())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Long id = jobPostingService.createJobPosting(dto.toEntity(foundUser));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/posts/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<JobPostingListResponseDto>> findAllJobPostings() {
        List<JobPostingListResponseDto> jobPostings = jobPostingService.getAllJobPostings()
                .stream().map(JobPostingListResponseDto::new).toList();

        return ResponseEntity.ok(jobPostings);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobPostingListResponseDto>> findAllJobPostingsByKeyword(@RequestParam("keyword") String keyword) {
        List<JobPostingListResponseDto> jobPostings = jobPostingService.getJobPostingByKeyword(keyword)
                .stream().map(JobPostingListResponseDto::new).toList();

        return ResponseEntity.ok(jobPostings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostingResponseDto> findJobPosting(@PathVariable Long id) {
        JobPosting jobPosting = jobPostingService.getJobPosting(id);
        List<Long> otherJobPostingIds = jobPostingService.getOtherJobPostings(id);

        JobPostingResponseDto dto = new JobPostingResponseDto(jobPosting, otherJobPostingIds);

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateJobPosting(@PathVariable Long id, JopPostingUpdateRequestDto dto) {
        jobPostingService.updateJobPosting(dto.toEntity(), id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPosting(id);
        return ResponseEntity.ok().build();
    }
}
