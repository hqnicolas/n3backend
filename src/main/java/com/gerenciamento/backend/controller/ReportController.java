package com.gerenciamento.backend.controller;

import com.gerenciamento.backend.exception.EntityNotFoundException;
import com.gerenciamento.backend.model.Donation;
import com.gerenciamento.backend.model.ReportFilter;
import com.gerenciamento.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/donation")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/reports/generate/{startDate}/{endDate}/{donationType}/{donor}")
    public ResponseEntity<List<Donation>> generateReport(
            @PathVariable String startDate,
            @PathVariable String endDate,
            @PathVariable String donationType,
            @PathVariable String donor) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            ReportFilter filter = new ReportFilter();
            filter.setStartDate(start);
            filter.setEndDate(end);
            filter.setDonationType(donationType);
            filter.setDonor(donor);

            List<Donation> report = reportService.generateReport(filter);
            return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.err.println("Entidade não encontrada: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reports/csv/{startDate}/{endDate}/{donationType}/{donor}")
    public ResponseEntity<byte[]> exportReportAsCsv(
            @PathVariable String startDate,
            @PathVariable String endDate,
            @PathVariable String donationType,
            @PathVariable String donor) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            ReportFilter filter = new ReportFilter();
            filter.setStartDate(start);
            filter.setEndDate(end);
            filter.setDonationType(donationType);
            filter.setDonor(donor);

            byte[] csv = reportService.exportReportAsCsv(filter);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=donation_report.csv")
                    .body(csv);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reports/pdf/{startDate}/{endDate}/{donationType}/{donor}")
    public ResponseEntity<byte[]> exportReportAsPdf(
            @PathVariable String startDate,
            @PathVariable String endDate,
            @PathVariable String donationType,
            @PathVariable String donor) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            byte[] pdf = reportService.exportReportAsPdf(start, end, donationType, donor);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=donation_report.pdf")
                    .body(pdf);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}