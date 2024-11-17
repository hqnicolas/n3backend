package com.gerenciamento.backend.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "mensagem")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "O ID do remetente é obrigatório")
    @Column(name = "remetente_id")
    private int remetenteId;

    @NotBlank(message = "O conteúdo é obrigatório")
    @Size(max = 500, message = "O conteúdo não pode exceder 500 caracteres")
    @Column(name = "conteudo")
    private String conteudo;

    @NotNull(message = "A data de envio é obrigatória")
    @PastOrPresent(message = "A data de envio não pode ser no futuro")
    @Column(name = "data_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEnvio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemetenteId() {
        return remetenteId;
    }

    public void setRemetenteId(int remetenteId) {
        this.remetenteId = remetenteId;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
