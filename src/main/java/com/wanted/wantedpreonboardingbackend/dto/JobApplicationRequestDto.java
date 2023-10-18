package com.wanted.wantedpreonboardingbackend.dto;

import com.wanted.wantedpreonboardingbackend.domain.JobApplication;
import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.domain.NormalUser;
import com.wanted.wantedpreonboardingbackend.domain.User;
import lombok.Data;

@Data
public class JobApplicationRequestDto {
    private Long userId;
    private Long jobPostingId;

    public JobApplication toEntity(JobPosting jobPosting, User user) {
        JobApplication jobApplication = JobApplication.builder()
                .jobPosting(jobPosting)
                .build();
        jobApplication.addNormalUser((NormalUser) user);

        return jobApplication;
    }
}
