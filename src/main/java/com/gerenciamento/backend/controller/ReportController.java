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
import java.util.List;

@RestController
@RequestMapping("/donation")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/reports/generate")
    public ResponseEntity<List<Donation>> generateReport(@RequestBody ReportFilter filter) {
        try {
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

    @PostMapping("/reports/csv")
    public ResponseEntity<byte[]> exportReportAsCsv(@RequestBody ReportFilter filter) {
        try {
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

    @PostMapping("/reports/pdf")
    public ResponseEntity<byte[]> exportReportAsPdf(@RequestBody ReportFilter filter) {
        try {
            byte[] pdf = reportService.exportReportAsPdf(filter);
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