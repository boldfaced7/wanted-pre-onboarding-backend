package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.JobApplication;
import com.wanted.wantedpreonboardingbackend.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public Long createJobApplication(JobApplication jobApplication) {
        jobApplicationRepository.findByNormalUserAndJobPosting(
                        jobApplication.getNormalUser(), jobApplication.getJobPosting())
                .ifPresent(ja -> new IllegalStateException("이미 지원한 채용 공고입니다."));

        return jobApplicationRepository.save(jobApplication).getId();
    }
}
