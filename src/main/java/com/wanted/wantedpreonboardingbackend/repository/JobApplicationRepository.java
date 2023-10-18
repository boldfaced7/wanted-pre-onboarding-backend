package com.wanted.wantedpreonboardingbackend.repository;

import com.wanted.wantedpreonboardingbackend.domain.JobApplication;
import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.domain.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    Optional<JobApplication> findByNormalUserAndJobPosting(NormalUser normalUser, JobPosting jobPosting);
}
