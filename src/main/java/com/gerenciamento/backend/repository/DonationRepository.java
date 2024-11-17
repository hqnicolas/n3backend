package com.gerenciamento.backend.repository;

import com.gerenciamento.backend.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT d FROM Donation d WHERE d.receiverDate BETWEEN :startDate AND :endDate AND d.type = :type AND d.donor = :donor")
    List<Donation> findByReceiverDateBetweenAndTypeAndDonor(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("type") String type, @Param("donor") String donor);

    Optional<Donation> findById(Long id);
}