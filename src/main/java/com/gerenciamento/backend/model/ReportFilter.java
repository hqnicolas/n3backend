package com.gerenciamento.backend.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    @NotBlank(message = "O tipo é obrigatório")
    @Pattern(regexp = "^(Material|Financeira)$", message = "O tipo deve ser um dos tipos permitidos (Material, Financeira)")
    private String donationType;

    @NotBlank(message = "Doador é necessário")
    private String donor;

    public ReportFilter(@NonNull LocalDate startDate, @NonNull LocalDate endDate, @NonNull String donationType, @NonNull String donor) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.donationType = donationType;
        this.donor = donor;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDonationType() {
        return donationType;
    }

    public String getDonor() {
        return donor;
    }
}