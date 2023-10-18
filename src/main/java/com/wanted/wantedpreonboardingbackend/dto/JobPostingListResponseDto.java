package com.wanted.wantedpreonboardingbackend.dto;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import lombok.Data;

@Data
public class JobPostingListResponseDto {

    private Long id;
    private String companyUserName;
    private String country;
    private String region;
    private String position;
    private int compensation;
    private String requiredSkill;

    public JobPostingListResponseDto(JobPosting jobPosting) {
        this.id = jobPosting.getId();
        this.companyUserName = jobPosting.getCompanyUser().getName();
        this.country = jobPosting.getCountry();
        this.region = jobPosting.getRegion();
        this.position = jobPosting.getPosition();
        this.compensation = jobPosting.getCompensation();
        this.requiredSkill = jobPosting.getRequiredSkill();
    }
}
