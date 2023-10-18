package com.wanted.wantedpreonboardingbackend.dto;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import lombok.Data;

import java.util.List;

@Data
public class JobPostingResponseDto {

    private Long id;
    private String companyUserName;
    private String country;
    private String region;
    private String position;
    private int compensation;
    private String requiredSkill;
    private String jobDescription;
    private List<Long> otherJobPostingIds;


    public JobPostingResponseDto(JobPosting jobPosting, List<Long> otherJobPostingIds) {
        this.id = jobPosting.getId();
        this.companyUserName = jobPosting.getCompanyUser().getName();
        this.country = jobPosting.getCountry();
        this.region = jobPosting.getRegion();
        this.position = jobPosting.getPosition();
        this.compensation = jobPosting.getCompensation();
        this.requiredSkill = jobPosting.getRequiredSkill();
        this.jobDescription = jobPosting.getJobDescription();
        this.otherJobPostingIds = otherJobPostingIds;
    }
}
