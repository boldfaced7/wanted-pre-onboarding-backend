package com.wanted.wantedpreonboardingbackend.repository;

import com.wanted.wantedpreonboardingbackend.domain.CompanyUser;
import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    List<JobPosting> findAllByCompanyUser(CompanyUser companyUser);
    List<JobPosting> findAllByCompanyUserNameContaining(String keyword);
    List<JobPosting> findAllByCountryContaining(String keyword);
    List<JobPosting> findAllByRegionContaining(String keyword);
    List<JobPosting> findAllByPositionContaining(String keyword);
    List<JobPosting> findAllByRequiredSkillContaining(String keyword);
}
