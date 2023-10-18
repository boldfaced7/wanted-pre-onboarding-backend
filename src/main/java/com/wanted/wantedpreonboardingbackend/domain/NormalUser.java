package com.wanted.wantedpreonboardingbackend.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@DiscriminatorValue("N")
@Entity
public class NormalUser extends User {

    @OneToMany(mappedBy = "normalUser")
    private List<JobApplication> jobApplications = new ArrayList<>();

}
