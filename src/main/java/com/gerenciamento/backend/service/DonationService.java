package com.gerenciamento.backend.service;

import com.gerenciamento.backend.exception.EntityNotFoundException;
import com.gerenciamento.backend.model.Donation;
import com.gerenciamento.backend.model.ReportFilter;
import com.gerenciamento.backend.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public Donation registerDonation(Donation donation) {
        try {
            System.out.println("Registrando Doação: " + donation);
            return donationRepository.save(donation);
        } catch (Exception e) {
            System.err.println("Erro Registrando Doação: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro Registrando Doação: " + e.getMessage(), e);
        }
    }

    public Page<Donation> getAllDonations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return donationRepository.findAll(pageable);
    }

    public Donation getDonationById(Long id) {
        try {
            System.out.println("Obtendo doação por ID: " + id);
            return donationRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Donation not found with id: " + id));
        } catch (EntityNotFoundException e) {
            System.err.println("Erro Obtendo doação por ID: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro Obtendo doação por ID: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro Obtendo doação por ID: " + e.getMessage(), e);
        }
    }

    public Donation updateDonation(Long id, Donation donation) {
        try {
            System.out.println("Updating Donation by ID: " + id);
            Donation existingDonation = getDonationById(id);
            existingDonation.setName(donation.getName());
            existingDonation.setType(donation.getType());
            existingDonation.setQuantity(donation.getQuantity());
            existingDonation.setDonor(donation.getDonor());
            existingDonation.setReceivalDate(donation.getReceivalDate());
            existingDonation.setExpiryDate(donation.getExpiryDate());
            existingDonation.setValidityPeriod(donation.getValidityPeriod());
            return donationRepository.save(existingDonation);
        } catch (EntityNotFoundException e) {
            System.err.println("Erro ao atualizar doação por ID: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro ao atualizar doação por ID: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar doação por ID: " + e.getMessage(), e);
        }
    }

    public void deleteDonation(Long id) {
        try {
            System.out.println("Excluindo doação por ID: " + id);
            donationRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            System.err.println("Error Excluindo doação por ID: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error Excluindo doação por ID: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error Excluindo doação por ID: " + e.getMessage(), e);
        }
    }

    public List<Donation> generateReport(ReportFilter filter) {
        try {
            System.out.println("Gerando relatório com filtro: " + filter);
            List<Donation> donations = donationRepository.findAll();

            List<Donation> filteredDonations = donations.stream()
                    .filter(donation -> donation.getReceivalDate().isAfter(filter.getStartDate()) || donation.getReceivalDate().isEqual(filter.getStartDate()))
                    .filter(donation -> donation.getReceivalDate().isBefore(filter.getEndDate()) || donation.getReceivalDate().isEqual(filter.getEndDate()))
                    .filter(donation -> donation.getType().equalsIgnoreCase(filter.getDonationType()))
                    .filter(donation -> donation.getDonor().equalsIgnoreCase(filter.getDonor()))
                    .collect(Collectors.toList());

            return filteredDonations;
        } catch (Exception e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar relatório: " + e.getMessage(), e);
        }
    }
}