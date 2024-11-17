package com.gerenciamento.backend.service;
import com.gerenciamento.backend.dto.DonationDTO;
import com.gerenciamento.backend.exception.EntityNotFoundException;
import com.gerenciamento.backend.model.Donation;
import com.gerenciamento.backend.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public DonationDTO registerDonation(DonationDTO donationDTO) {
        try {
            System.out.println("Registrando Doação: " + donationDTO);
            Donation donation = mapToDonation(donationDTO);
            Donation savedDonation = donationRepository.save(donation);
            return mapToDonationDTO(savedDonation);
        } catch (Exception e) {
            System.err.println("Erro Registrando Doação: " + e.getMessage());
            throw new RuntimeException("Erro Registrando Doação: " + e.getMessage(), e);
        }
    }

    public Page<DonationDTO> getAllDonations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Donation> donationPage = donationRepository.findAll(pageable);
        return donationPage.map(this::mapToDonationDTO);
    }

    public DonationDTO getDonationById(Long id) {
        try {
            System.out.println("Obtendo doação por ID: " + id);
            Donation donation = donationRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("id não encontrado: " + id));
            return mapToDonationDTO(donation);
        } catch (EntityNotFoundException e) {
            System.err.println("Erro Obtendo doação por ID: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro Obtendo doação por ID: " + e.getMessage());
            throw new RuntimeException("Erro Obtendo doação por ID: " + e.getMessage(), e);
        }
    }

    public DonationDTO updateDonation(Long id, DonationDTO donationDTO) {
        try {
            System.out.println("Atualizando Doação por ID: " + id);
            Donation existingDonation = donationRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("id não encontrado: " + id));
            existingDonation.setName(donationDTO.getName());
            existingDonation.setType(donationDTO.getType());
            existingDonation.setQuantity(donationDTO.getQuantity());
            existingDonation.setDonor(donationDTO.getDonor());
            existingDonation.setReceiverDate(donationDTO.getReceiverDate());
            existingDonation.setExpiryDate(donationDTO.getExpiryDate());
            existingDonation.setValidityPeriod(donationDTO.getValidityPeriod());
            Donation updatedDonation = donationRepository.save(existingDonation);
            return mapToDonationDTO(updatedDonation);
        } catch (EntityNotFoundException e) {
            System.err.println("Erro ao atualizar doação por ID: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro ao atualizar doação por ID: " + e.getMessage());
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
            throw new RuntimeException("Error Excluindo doação por ID: " + e.getMessage(), e);
        }
    }

    private Donation mapToDonation(DonationDTO donationDTO) {
        Donation donation = new Donation();
        donation.setName(donationDTO.getName());
        donation.setType(donationDTO.getType());
        donation.setQuantity(donationDTO.getQuantity());
        donation.setDonor(donationDTO.getDonor());
        donation.setReceiverDate(donationDTO.getReceiverDate());
        donation.setExpiryDate(donationDTO.getExpiryDate());
        donation.setValidityPeriod(donationDTO.getValidityPeriod());
        return donation;
    }

    private DonationDTO mapToDonationDTO(Donation donation) {
        DonationDTO donationDTO = new DonationDTO();
        donationDTO.setId(donation.getId());
        donationDTO.setName(donation.getName());
        donationDTO.setType(donation.getType());
        donationDTO.setQuantity(donation.getQuantity());
        donationDTO.setDonor(donation.getDonor());
        donationDTO.setReceiverDate(donation.getReceiverDate());
        donationDTO.setExpiryDate(donation.getExpiryDate());
        donationDTO.setValidityPeriod(donation.getValidityPeriod());
        return donationDTO;
    }
}