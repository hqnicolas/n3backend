package com.gerenciamento.backend.dto;

import java.util.Date;

public class MensagemDTO {
    private String conteudo;
    private Date dataEnvio;

    public MensagemDTO(String conteudo, Date dataEnvio) {
        this.conteudo = conteudo;
        this.dataEnvio = dataEnvio;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }
}
