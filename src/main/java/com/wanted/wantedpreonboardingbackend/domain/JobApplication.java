package com.wanted.wantedpreonboardingbackend.domain;

import jakarta.persistence.*;
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
    @JoinColumn(name = "user_id")
    private NormalUser normalUser;

    public void addNormalUser(NormalUser normalUser) {
        this.normalUser = normalUser;
        normalUser.getJobApplications().add(this);
    }
}
