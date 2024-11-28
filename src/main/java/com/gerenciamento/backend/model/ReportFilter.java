package com.gerenciamento.backend.model;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Getter
@Data
@NoArgsConstructor
public class ReportFilter {

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotBlank(message = "O tipo é obrigatório")
    @Pattern(regexp = "^(Material|Financeira)$", message = "O tipo deve ser um dos tipos permitidos (Material, Financeira)")
    private String donationType;

    @NotBlank(message = "Doador é necessário")
    private String donor;

    public ReportFilter(@NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull String donationType, @NotNull String donor) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.donationType = donationType;
        this.donor = donor;
    }

    public @NotNull @PastOrPresent LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull @PastOrPresent LocalDate startDate) {
        this.startDate = startDate;
    }

    public @NotNull @FutureOrPresent LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull @FutureOrPresent LocalDate endDate) {
        this.endDate = endDate;
    }

    public @NotBlank(message = "O tipo é obrigatório") @Pattern(regexp = "^(Material|Financeira)$", message = "O tipo deve ser um dos tipos permitidos (Material, Financeira)") String getDonationType() {
        return donationType;
    }

    public void setDonationType(@NotBlank(message = "O tipo é obrigatório") @Pattern(regexp = "^(Material|Financeira)$", message = "O tipo deve ser um dos tipos permitidos (Material, Financeira)") String donationType) {
        this.donationType = donationType;
    }

    public @NotBlank(message = "Doador é necessário") String getDonor() {
        return donor;
    }

    public void setDonor(@NotBlank(message = "Doador é necessário") String donor) {
        this.donor = donor;
    }
}