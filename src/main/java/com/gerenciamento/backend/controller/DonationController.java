package com.gerenciamento.backend.controller;
import com.gerenciamento.backend.dto.DonationDTO;
import com.gerenciamento.backend.model.Donation;
import com.gerenciamento.backend.service.DonationService;
import com.gerenciamento.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/donation")
@Validated
public class DonationController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<Map<String, String>> registerDonation(@RequestBody DonationDTO donationDTO) {
        System.out.println("Solicitação de registro de doação recebida: " + donationDTO);
        try {
            System.out.println("DonationDTO Details: Name: " + donationDTO.getName() +
                               ", Type: " + donationDTO.getType() +
                               ", Quantity: " + donationDTO.getQuantity() +
                               ", Donor: " + donationDTO.getDonor() +
                               ", Receiver Date: " + donationDTO.getReceiverDate() +
                               ", Expiry Date: " + donationDTO.getExpiryDate() +
                               ", Validity Period: " + donationDTO.getValidityPeriod());

            System.out.println("Entrando no serviço de registro de doação");
            Donation donation = new Donation();
            donation.setName(donationDTO.getName());
            donation.setType(donationDTO.getType());
            donation.setQuantity(donationDTO.getQuantity());
            donation.setDonor(donationDTO.getDonor());
            donation.setReceiverDate(donationDTO.getReceiverDate());
            donation.setExpiryDate(donationDTO.getExpiryDate());
            donation.setValidityPeriod(donationDTO.getValidityPeriod());

            DonationDTO createdDonation = donationService.registerDonation(donationDTO);
            System.out.println("Doação registrada com sucesso: " + createdDonation);
            return new ResponseEntity<>(Collections.singletonMap("mensagem", "Doação registrada com sucesso!"), HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            System.err.println("Validação falhou: " + e.getMessage());
            Map<String, String> errors = new HashMap<>();
            for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
                System.err.println("Violação: " + violation.getPropertyPath() + " - " + violation.getMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.err.println("Erro ao registrar doação: " + e.getMessage() + " - Rastreamento de pilha: " + e.getStackTrace());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<DonationDTO>> getAllDonations(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "20") int size) {
        System.out.println("Solicitação para receber todas as doações com a página:" + page + " e tamanho:" + size);
        try {
            Page<DonationDTO> donationsPage = donationService.getAllDonations(page, size);
            List<DonationDTO> donationDTOs = donationsPage.getContent().stream()
                .map(donation -> {
                    DonationDTO dto = new DonationDTO();
                    dto.setId(donation.getId());
                    dto.setName(donation.getName());
                    dto.setType(donation.getType());
                    dto.setQuantity(donation.getQuantity());
                    dto.setDonor(donation.getDonor());
                    dto.setReceiverDate(donation.getReceiverDate());
                    dto.setExpiryDate(donation.getExpiryDate());
                    dto.setValidityPeriod(donation.getValidityPeriod());
                    return dto;
                }).collect(Collectors.toList());

            System.out.println("Total de doações obtidas: " + donationsPage.getTotalElements());
            return new ResponseEntity<>(donationDTOs, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Erro ao obter doações: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationDTO> getDonationById(@PathVariable Long id) {
        DonationDTO donationDTO = donationService.getDonationById(id);
        return ResponseEntity.ok(donationDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonationDTO> updateDonation(@PathVariable Long id, @RequestBody DonationDTO donationDTO) {
        DonationDTO updatedDonationDTO = donationService.updateDonation(id, donationDTO);
        return ResponseEntity.ok(updatedDonationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return ResponseEntity.noContent().build();
    }
}