package com.gerenciamento.backend.controller;

import com.gerenciamento.backend.exception.EntityNotFoundException;
import com.gerenciamento.backend.model.ReportFilter;
import com.gerenciamento.backend.model.Donation;
import com.gerenciamento.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation")

public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/reports/generate")
    public ResponseEntity<List<Donation>> generateReport(@RequestBody ReportFilter filter) {
        try {
            System.out.println("Solicitação para gerar relatório com filtro: " + filter);
            List<Donation> report = reportService.generateReport(filter);
            System.out.println("Relatório gerado com sucesso: " + report);
            return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.err.println("Entidade não encontrada: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
            System.err.println("Rastreamento de pilha: " + e.getStackTrace());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reports/csv")
    public ResponseEntity<byte[]> exportReportAsCsv(@RequestBody ReportFilter filter) {
        try {
            System.out.println("Solicitar exportação do relatório como CSV com filtro: " + filter);
            byte[] csv = reportService.exportReportAsCsv(filter);
            System.out.println("CSV relatório exportado com sucesso");
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=donation_report.csv")
                    .body(csv);
        } catch (EntityNotFoundException e) {
            System.err.println("Entidade não encontrada: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println("Erro ao exportar relatório como CSV: " + e.getMessage());
            System.err.println("Rastreamento de pilha: " + e.getStackTrace());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reports/pdf")
    public ResponseEntity<byte[]> exportReportAsPdf(@RequestBody ReportFilter filter) {
        try {
            System.out.println("Solicitar exportação do relatório como PDF com filtro: " + filter);
            byte[] pdf = reportService.exportReportAsPdf(filter);
            System.out.println("PDF relatório exportado com sucesso");
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=donation_report.pdf")
                    .body(pdf);
        } catch (EntityNotFoundException e) {
            System.err.println("Entidade não encontrada: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println("Erro ao exportar relatório como PDF: " + e.getMessage());
            System.err.println("Rastreamento de pilha: " + e.getStackTrace());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}