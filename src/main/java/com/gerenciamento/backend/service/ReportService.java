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
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private DonationRepository donationRepository;

    @Transactional(readOnly = true)
    public List<Donation> generateReport(ReportFilter filter) {
        try {
            System.out.println("Gerando relatório com filtro: " + filter);
            System.out.println("dataInicio: " + filter.getStartDate());
            System.out.println("dataFim: " + filter.getEndDate());

            if (filter.getStartDate() == null || filter.getEndDate() == null) {
                throw new IllegalArgumentException("A data de início e a data de término não devem ser nulas");
            }

            if (filter.getStartDate().isAfter(filter.getEndDate())) {
                throw new IllegalArgumentException("A data de início deve ser anterior ou igual à data de término");
            }

            List<Donation> report = donationRepository.findByReceivalDateBetweenAndTypeAndDonor(
                    filter.getStartDate(),
                    filter.getEndDate(),
                    filter.getDonationType(),
                    filter.getDonor()
            );

            System.out.println("Relatório gerado com sucesso: " + report);
            return report;
        } catch (EntityNotFoundException e) {
            System.err.println("Entidade Não encontrada: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar relatório", e);
        } catch (Exception e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }

    @Transactional(readOnly = true)
    public byte[] exportReportAsCsv(ReportFilter filter) {
        try {
            System.out.println("Exportando o relatório como CSV filtrado: " + filter);
            System.out.println("dataInicio: " + filter.getStartDate());
            System.out.println("dataFim: " + filter.getEndDate());

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
                        donation.getReceivalDate(),
                        donation.getExpiryDate(),
                        donation.getValidityPeriod()
                );
            }
            printer.flush();
            System.out.println("CSV relatório exportado com sucesso");
            return out.toByteArray();
        } catch (EntityNotFoundException e) {
            System.err.println("Entidade Não encontrada: " + e.getMessage());
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
    public byte[] exportReportAsPdf(ReportFilter filter) {
        try {
            System.out.println("Exportando relatório como PDF filtrado: " + filter);
            System.out.println("dataInicio: " + filter.getStartDate());
            System.out.println("dataFim: " + filter.getEndDate());

            List<Donation> report = generateReport(filter);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Relatório de Doações"));
            document.add(new Paragraph("Intervalo de datas: " + filter.getStartDate() + " - " + filter.getEndDate()));
            document.add(new Paragraph("Tipo: " + filter.getDonationType()));
            document.add(new Paragraph("Doador: " + filter.getDonor()));
            document.add(new Paragraph("\nDoações:"));
            for (Donation donation : report) {
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
            System.out.println("PDF relatório exportado com sucesso");
            return out.toByteArray();
        } catch (EntityNotFoundException e) {
            System.err.println("Entidade Não encontrada: " + e.getMessage());
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