package com.suresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suresh.entities.EligibilityDetails;

public interface CitizenRepository extends JpaRepository<EligibilityDetails, String>{

}
