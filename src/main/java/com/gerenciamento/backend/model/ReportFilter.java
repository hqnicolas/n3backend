package com.gerenciamento.backend.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Data
@NoArgsConstructor
public class ReportFilter {

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "dd/MM/yyyy")
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

}