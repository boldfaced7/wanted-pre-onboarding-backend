package com.wanted.wantedpreonboardingbackend.dto;

import com.wanted.wantedpreonboardingbackend.domain.CompanyUser;
import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.domain.User;
import lombok.Data;

@Data
public class JobPostingRequestDto {

    private String companyName;
    private String country;
    private String region;
    private String position;
    private int compensation;
    private String requiredSkill;
    private String jobDescription;


    public JobPosting toEntity(User user) {
        JobPosting jobPosting = JobPosting.builder()
                .country(country)
                .region(region)
                .position(position)
                .compensation(compensation)
                .requiredSkill(requiredSkill)
                .jobDescription(jobDescription)
                .build();

        jobPosting.addCompanyUser((CompanyUser) user);
        return jobPosting;
    }
}
