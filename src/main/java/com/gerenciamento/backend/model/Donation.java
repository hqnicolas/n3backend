package com.gerenciamento.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Data
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Pattern(regexp = "^(Material|Financeira)$", message = "O tipo deve ser um dos tipos permitidos (Material, Financeira)")
    private String type;

    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private int quantity;

    @NotBlank(message = "Doador é necessário")
    private String donor;

    @NotNull(message = "Data de recebimento é necessária")
    @Column(name = "receiver_date")
    private LocalDate receiverDate;

    @Setter
    @Future(message = "A data de validade deve ser no futuro")
    private LocalDate expiryDate;

    @Min(value = 1, message = "O período de validade deve ser menor que 3 anos")
    @Max(value = 999, message = "O período de validade deve ser menor que 3 anos")
    private int validityPeriod;

    @PrePersist
    public void setExpiryDateFromValidityPeriod() {
        this.expiryDate = this.receiverDate.plusDays(this.validityPeriod);
    }
}