package com.gerenciamento.backend.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class DonationDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Pattern(regexp = "^(Material|Financeira)$", message = "O tipo deve ser um dos tipos permitidos (Material, Financeira)")
    private String type;

    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private int quantity;

    @NotBlank(message = "Doador é necessário")
    private String donor;

    @NotNull(message = "Data de recebimento é necessária")
    private LocalDate ReceiverDate;

    @Future(message = "A data de validade deve ser no futuro")
    private LocalDate expiryDate;

    @Min(value = 1, message = "O período de validade deve ser menor que 3 anos")
    @Max(value = 999, message = "O período de validade deve ser menor que 3 anos")
    private int validityPeriod;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public LocalDate getReceiverDate() {
        return ReceiverDate;
    }

    public void setReceiverDate(LocalDate ReceiverDate) {
        this.ReceiverDate = ReceiverDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(int validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
}