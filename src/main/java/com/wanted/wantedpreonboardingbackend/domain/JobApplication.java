package com.wanted.wantedpreonboardingbackend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class JobApplication extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private NormalUser normalUser;

    @Builder
    public JobApplication(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    public void addNormalUser(NormalUser normalUser) {
        this.normalUser = normalUser;
        normalUser.getJobApplications().add(this);
    }


}
