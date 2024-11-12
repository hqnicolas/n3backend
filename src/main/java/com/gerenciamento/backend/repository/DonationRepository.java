package com.gerenciamento.backend.repository;

import com.gerenciamento.backend.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByReceivalDateBetweenAndTypeAndDonor(
            LocalDate startDate,
            LocalDate endDate,
            String donationType,
            String donor
    );
}