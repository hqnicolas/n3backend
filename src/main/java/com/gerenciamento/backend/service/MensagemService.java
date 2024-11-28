package com.gerenciamento.backend.service;

import com.gerenciamento.backend.model.Mensagem;
import com.gerenciamento.backend.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    public List<Mensagem> getAllMensagens() {
        return mensagemRepository.findAll();
    }

    public Optional<Mensagem> getMensagemById(Integer id) {
        return mensagemRepository.findById(id);
    }

    public Mensagem createMensagem(Mensagem mensagem) {
        Date dataEnvio = new Date();
        mensagem.setDataEnvio(dataEnvio);
        return mensagemRepository.save(mensagem);
    }

    public Optional<Mensagem> updateMensagem(Integer id, Mensagem mensagemDetails) {
        return mensagemRepository.findById(id).map(mensagem -> {
            mensagem.setRemetenteId(mensagemDetails.getRemetenteId());
            mensagem.setConteudo(mensagemDetails.getConteudo());
            mensagem.setDataEnvio(mensagemDetails.getDataEnvio());
            return mensagemRepository.save(mensagem);
        });
    }

    public boolean deleteMensagem(Integer id) {
        return mensagemRepository.findById(id).map(mensagem -> {
            mensagemRepository.delete(mensagem);
            return true;
        }).orElse(false);
    }
}
