package com.wanted.wantedpreonboardingbackend.service;

import com.wanted.wantedpreonboardingbackend.domain.JobPosting;
import com.wanted.wantedpreonboardingbackend.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    public Long createJobPosting(JobPosting jobPosting) {
        return jobPostingRepository.save(jobPosting).getId();
    }

    public void updateJobPosting(JobPosting jobPosting, Long id) {
        JobPosting foundJobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        authorizeJobPostingCompany(foundJobPosting);

        foundJobPosting.updateJobPosting(jobPosting);
    }

    public void deleteJobPosting(Long id) {
        JobPosting foundJobPosting = getJobPosting(id);
        authorizeJobPostingCompany(foundJobPosting);
        jobPostingRepository.delete(foundJobPosting);
    }

    @Transactional(readOnly = true)
    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public JobPosting getJobPosting(Long id) {
        return jobPostingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용 공고입니다."));
    }

    @Transactional(readOnly = true)
    public List<Long> getOtherJobPostings(Long id) {
        JobPosting jobPosting = getJobPosting(id);

        return jobPostingRepository.findAllByCompanyUser(jobPosting.getCompanyUser())
                .stream().map(JobPosting::getId).toList();
    }

    @Transactional(readOnly = true)
    public List<JobPosting> getJobPostingByKeyword(String keyword) {
        List<JobPosting> results = jobPostingRepository.findAllByCompanyUserNameContaining(keyword);
        results.addAll(jobPostingRepository.findAllByCountryContaining(keyword));
        results.addAll(jobPostingRepository.findAllByRegionContaining(keyword));
        results.addAll(jobPostingRepository.findAllByPositionContaining(keyword));
        results.addAll(jobPostingRepository.findAllByRequiredSkillContaining(keyword));

        return results;
    }

    private void authorizeJobPostingCompany(JobPosting jobPosting) {
        /*
        //==사용자 인증 절차는 생략==//
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (jobPosting.getCompanyUser().getName().equals(userName)) {
            throw new IllegalArgumentException("Not authorized");
        }

         */
    }
}
