package com.satyam.ChallangeApps;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChallengeRepo extends JpaRepository<Challenge, Long> {

    Optional<Challenge> findByMonthIgnoreCase(String month);
}
