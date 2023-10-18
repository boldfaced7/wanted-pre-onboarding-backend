package com.wanted.wantedpreonboardingbackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@DiscriminatorValue("C")
@Entity
public class CompanyUser extends User {

    @OneToMany(mappedBy = "companyUser")
    private List<JobPosting> jobPostings = new ArrayList<>();
}
