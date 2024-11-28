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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome é obrigatório") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Nome é obrigatório") String name) {
        this.name = name;
    }

    public @Pattern(regexp = "^(Material|Financeira)$", message = "O tipo deve ser um dos tipos permitidos (Material, Financeira)") String getType() {
        return type;
    }

    public void setType(@Pattern(regexp = "^(Material|Financeira)$", message = "O tipo deve ser um dos tipos permitidos (Material, Financeira)") String type) {
        this.type = type;
    }

    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 1, message = "A quantidade deve ser no mínimo 1") int quantity) {
        this.quantity = quantity;
    }

    public @NotBlank(message = "Doador é necessário") String getDonor() {
        return donor;
    }

    public void setDonor(@NotBlank(message = "Doador é necessário") String donor) {
        this.donor = donor;
    }

    public @NotNull(message = "Data de recebimento é necessária") LocalDate getReceiverDate() {
        return receiverDate;
    }

    public void setReceiverDate(@NotNull(message = "Data de recebimento é necessária") LocalDate receiverDate) {
        this.receiverDate = receiverDate;
    }

    public @Future(message = "A data de validade deve ser no futuro") LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(@Future(message = "A data de validade deve ser no futuro") LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Min(value = 1, message = "O período de validade deve ser menor que 3 anos")
    @Max(value = 999, message = "O período de validade deve ser menor que 3 anos")
    public int getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(@Min(value = 1, message = "O período de validade deve ser menor que 3 anos") @Max(value = 999, message = "O período de validade deve ser menor que 3 anos") int validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
}