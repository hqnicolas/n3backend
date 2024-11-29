package com.gerenciamento.backend.service;

import com.gerenciamento.backend.exception.EntityNotFoundException;
import com.gerenciamento.backend.model.Donation;
import com.gerenciamento.backend.model.ReportFilter;
import com.gerenciamento.backend.repository.DonationRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private DonationRepository donationRepository;

    @Transactional(readOnly = true)
    public List<Donation> generateReport(ReportFilter filter) {
        try {
            if (filter.getStartDate() == null || filter.getEndDate() == null) {
                throw new IllegalArgumentException("A data de início e a data de término não devem ser nulas");
            }

            if (filter.getStartDate().isAfter(filter.getEndDate())) {
                throw new IllegalArgumentException("A data de início deve ser anterior ou igual à data de término");
            }

            return donationRepository.findByReceiverDateBetweenAndTypeAndDonor(
                    filter.getStartDate(),
                    filter.getEndDate(),
                    filter.getDonationType(),
                    filter.getDonor()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }

    @Transactional(readOnly = true)
    public byte[] exportReportAsCsv(ReportFilter filter) {
        try {
            List<Donation> report = generateReport(filter);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter printer = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT);
            printer.printRecord("Nome", "Tipo", "Quantidade", "Doador", "Data de recebimento", "Data de validade", "Período de validade");
            for (Donation donation : report) {
                printer.printRecord(
                        donation.getName(),
                        donation.getType(),
                        donation.getQuantity(),
                        donation.getDonor(),
                        donation.getReceiverDate(),
                        donation.getExpiryDate(),
                        donation.getValidityPeriod()
                );
            }
            printer.flush();
            return out.toByteArray();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Erro ao exportar relatório como CSV: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao exportar relatório como CSV", e);
        }
    }

    @Transactional(readOnly = true)
    public byte[] exportReportAsPdf(LocalDate startDate, LocalDate endDate, String donationType, String donor) {
        try {
            ReportFilter filter = new ReportFilter();
            filter.setStartDate(startDate);
            filter.setEndDate(endDate);
            filter.setDonationType(donationType);
            filter.setDonor(donor);

            List<Donation> report = generateReport(filter);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Relatório de Doações"));
            document.add(new Paragraph("Intervalo de datas: " + startDate + " - " + endDate));
            document.add(new Paragraph("Tipo: " + donationType));
            document.add(new Paragraph("Doador: " + donor));
            document.add(new Paragraph("\nDoações:"));
            for (Donation donation : report) {
                document.add(new Paragraph("Nome: " + donation.getName()));
                document.add(new Paragraph("Tipo: " + donation.getType()));
                document.add(new Paragraph("Quantidade: " + donation.getQuantity()));
                document.add(new Paragraph("Doador: " + donation.getDonor()));
                document.add(new Paragraph("Data de Recebimento: " + donation.getReceiverDate()));
                document.add(new Paragraph("Data de Validade: " + donation.getExpiryDate()));
                document.add(new Paragraph("Período de Validade: " + donation.getValidityPeriod()));
                document.add(new Paragraph("\n"));
            }
            document.close();
            return out.toByteArray();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Erro ao exportar relatório como PDF: " + e.getMessage(), e);
        } catch (DocumentException e) {
            throw new RuntimeException("Erro ao exportar relatório como PDF", e);
        }
    }
}