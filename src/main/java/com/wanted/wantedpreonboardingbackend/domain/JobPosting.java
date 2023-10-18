package com.wanted.wantedpreonboardingbackend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class JobPosting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_posting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String companyName;
    private String country;
    private String region;
    private String position;
    private int compensation;
    private String requiredSkill;

    @Column(length = 1000)
    private String jobDescription;

    @Builder
    public JobPosting(String companyName, String country, String region,
                      String position, int compensation, String requiredSkill, String jobDescription) {
        this.companyName = companyName;
        this.country = country;
        this.region = region;
        this.position = position;
        this.compensation = compensation;
        this.requiredSkill = requiredSkill;
        this.jobDescription = jobDescription;
    }

    public void addCompany(Company company) {
        this.company = company;
        company.getJobPostings().add(this);
    }

    public void updateJobPosting(String companyName, String country, String region,
                       String position, int compensation, String requiredSkill, String jobDescription) {
        this.companyName = companyName;
        this.country = country;
        this.region = region;
        this.position = position;
        this.compensation = compensation;
        this.requiredSkill = requiredSkill;
        this.jobDescription = jobDescription;
    }
}
