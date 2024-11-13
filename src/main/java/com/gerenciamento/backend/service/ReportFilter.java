package com.gerenciamento.backend.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReportFilter {

    @NotNull
    @PastOrPresent
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    private LocalDate endDate;

    @NotBlank
    private String donationType;

    @NotBlank
    private String donor;

    public ReportFilter(@NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull String donationType, @NotNull String donor) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.donationType = donationType;
        this.donor = donor;
    }
}