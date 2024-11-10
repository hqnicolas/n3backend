package com.gerenciamento.backend.controller;

import com.gerenciamento.backend.exception.EntityNotFoundException;
import com.gerenciamento.backend.model.Donation;
import com.gerenciamento.backend.model.ReportFilter;
import com.gerenciamento.backend.service.DonationService;
import com.gerenciamento.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/donation")
@Validated
public class DonationController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<Donation> registerDonation(@RequestBody Donation donation) {
        System.out.println("Solicitação de registro de doação:" + donation);
        try {
            Donation createdDonation = donationService.registerDonation(donation);
            System.out.println("Doação registrada com sucesso:" + createdDonation);
            return new ResponseEntity<>(createdDonation, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Erro ao registrar doação:" + e.getMessage() + "- Rastreamento de pilha:" + e.getStackTrace());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Donation>> getAllDonations(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size) {
        System.out.println("Solicitação para receber todas as doações com a página:" + page + " e tamanho:" + size);
        try {
            Page<Donation> donationPage = donationService.getAllDonations(page, size);
            List<Donation> donations = donationPage.getContent();
            System.out.println("Doações obtidas com sucesso:" + donations);
            return new ResponseEntity<>(donations, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Erro ao buscar doações:" + e.getMessage() + "- Rastreamento de pilha:" + e.getStackTrace());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> getDonationById(@PathVariable Long id) {
        System.out.println("Solicitação para receber doação por ID: " + id);
        try {
            Donation donation = donationService.getDonationById(id);
            System.out.println("Doação obtida com sucesso: " + donation);
            return new ResponseEntity<>(donation, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.err.println("Doação não encontrada:" + e.getMessage() + "- Rastreamento de pilha:" + e.getStackTrace());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            System.err.println("Erro ao buscar doação: " + e.getMessage() + "- Rastreamento de pilha:" + e.getStackTrace());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donation> updateDonation(@PathVariable Long id, @RequestBody Donation donation) {
        System.out.println("Solicitação de atualização de doação por ID: " + id);
        try {
            Donation updatedDonation = donationService.updateDonation(id, donation);
            System.out.println("Doação atualizada com sucesso:" + updatedDonation);
            return new ResponseEntity<>(updatedDonation, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.err.println("Doação não encontrada:" + e.getMessage() + "- Rastreamento de pilha:" + e.getStackTrace());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar doação:" + e.getMessage() + "- Rastreamento de pilha:" + e.getStackTrace());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
        System.out.println("Solicitação de exclusão de doação por ID: " + id);
        try {
            donationService.deleteDonation(id);
            System.out.println("Doação excluída com sucesso: " + id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.err.println("Doação não encontrada:" + e.getMessage() + "- Rastreamento de pilha:" + e.getStackTrace());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            System.err.println("Erro ao excluir doação:" + e.getMessage() + "- Rastreamento de pilha:" + e.getStackTrace());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/report")
    public ResponseEntity<List<Donation>> generateReport(@RequestBody ReportFilter reportFilter) {
        System.out.println("Solicitação para gerar relatório com filtro:" + reportFilter);
        try {
            List<Donation> report = reportService.generateReport(reportFilter);
            System.out.println("Relatório gerado com sucesso:" + report);
            return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Erro ao gerar relatório:" + e.getMessage() + "- Rastreamento de pilha:" + e.getStackTrace());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/report/csv")
    public ResponseEntity<byte[]> exportReportAsCsv(@RequestBody ReportFilter reportFilter) {
        System.out.println("Solicitação para exportar relatório como CSV com filtro:" + reportFilter);
        try {
            byte[] csv = reportService.exportReportAsCsv(reportFilter);
            System.out.println("CSV relatório exportado com sucesso");
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=donation_report.csv")
                    .body(csv);
        } catch (Exception e) {
            System.err.println("Erro ao exportar relatório como CSV: " + e.getMessage() + "- Rastreamento de pilha:" + e.getStackTrace());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage().getBytes());
        }
    }

    @PostMapping("/report/pdf")
    public ResponseEntity<byte[]> exportReportAsPdf(@RequestBody ReportFilter reportFilter) {
        System.out.println("Solicitar exportação do relatório como PDF com filtro: " + reportFilter);
        try {
            byte[] pdf = reportService.exportReportAsPdf(reportFilter);
            System.out.println("PDF relatório exportado com sucesso");
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=donation_report.pdf")
                    .body(pdf);
        } catch (Exception e) {
            System.err.println("Erro ao exportar relatório como PDF: " + e.getMessage() + "- Rastreamento de pilha:" + e.getStackTrace());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage().getBytes());
        }
    }
}