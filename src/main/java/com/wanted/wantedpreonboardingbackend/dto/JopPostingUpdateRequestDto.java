package com.wanted.wantedpreonboardingbackend.dto;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import lombok.Data;

@Data
public class JopPostingUpdateRequestDto {

    private String companyName;
    private String country;
    private String region;
    private String position;
    private int compensation;
    private String requiredSkill;
    private String jobDescription;


    public JobPosting toEntity() {
        return JobPosting.builder()
                .country(country)
                .region(region)
                .position(position)
                .compensation(compensation)
                .requiredSkill(requiredSkill)
                .jobDescription(jobDescription)
                .build();
    }
}
