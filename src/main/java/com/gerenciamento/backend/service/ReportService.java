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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private DonationRepository donationRepository;

    @Transactional(readOnly = true)
    public List<Donation> generateReport(ReportFilter reportFilter) {
        try {
            List<Donation> donations = donationRepository.findAll();

            List<Donation> filteredDonations = donations.stream()
                    .filter(donation -> donation.getReceivalDate().isAfter(reportFilter.getStartDate()) || donation.getReceivalDate().isEqual(reportFilter.getStartDate()))
                    .filter(donation -> donation.getReceivalDate().isBefore(reportFilter.getEndDate()) || donation.getReceivalDate().isEqual(reportFilter.getEndDate()))
                    .filter(donation -> donation.getType().equalsIgnoreCase(reportFilter.getDonationType()))
                    .filter(donation -> donation.getDonor().equalsIgnoreCase(reportFilter.getDonor()))
                    .collect(Collectors.toList());

            return filteredDonations;
        } catch (EntityNotFoundException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
            e.printStackTrace();
            throw new EntityNotFoundException("Erro ao gerar relatório: " + e.getMessage(), e);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.err.println("Erro ao gerar relatório: " + sw.toString());
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }

    @Transactional(readOnly = true)
    public byte[] exportReportAsCsv(ReportFilter reportFilter) {
        try {
            List<Donation> donations = generateReport(reportFilter);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter printer = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT);
            printer.printRecord("Nome", "Tipo", "Quantidade", "Doador", "Data de recebimento", "Data de validade", "Período de validade");
            for (Donation donation : donations) {
                printer.printRecord(
                        donation.getName(),
                        donation.getType(),
                        donation.getQuantity(),
                        donation.getDonor(),
                        donation.getReceivalDate(),
                        donation.getExpiryDate(),
                        donation.getValidityPeriod()
                );
            }
            printer.flush();
            return out.toByteArray();
        } catch (EntityNotFoundException e) {
            System.err.println("Erro ao exportar relatório como CSV: " + e.getMessage());
            e.printStackTrace();
            throw new EntityNotFoundException("Erro ao exportar relatório como CSV: " + e.getMessage(), e);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.err.println("Erro ao exportar relatório como CSV: " + sw.toString());
            throw new RuntimeException("Erro ao exportar relatório como CSV", e);
        }
    }

    @Transactional(readOnly = true)
    public byte[] exportReportAsPdf(ReportFilter reportFilter) {
        try {
            List<Donation> donations = generateReport(reportFilter);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Relatório de Doações"));
            document.add(new Paragraph("Intervalo de datas: " + reportFilter.getStartDate() + " - " + reportFilter.getEndDate()));
            document.add(new Paragraph("Tipo: " + reportFilter.getDonationType()));
            document.add(new Paragraph("Doador: " + reportFilter.getDonor()));
            document.add(new Paragraph("\nDoações:"));
            for (Donation donation : donations) {
                document.add(new Paragraph("Nome: " + donation.getName()));
                document.add(new Paragraph("Tipo: " + donation.getType()));
                document.add(new Paragraph("Quantidade: " + donation.getQuantity()));
                document.add(new Paragraph("Doador: " + donation.getDonor()));
                document.add(new Paragraph("Data de Recebimento: " + donation.getReceivalDate()));
                document.add(new Paragraph("Data de Validade: " + donation.getExpiryDate()));
                document.add(new Paragraph("Período de Validade: " + donation.getValidityPeriod()));
                document.add(new Paragraph("\n"));
            }
            document.close();
            return out.toByteArray();
        } catch (EntityNotFoundException e) {
            System.err.println("Erro ao exportar relatório como PDF: " + e.getMessage());
            e.printStackTrace();
            throw new EntityNotFoundException("Erro ao exportar relatório como PDF: " + e.getMessage(), e);
        } catch (DocumentException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.err.println("Erro ao exportar relatório como PDF: " + sw.toString());
            throw new RuntimeException("Erro ao exportar relatório como PDF", e);
        }
    }
}