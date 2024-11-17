package com.gerenciamento.backend.controller;
import com.gerenciamento.backend.dto.MensagemDTO;
import com.gerenciamento.backend.model.Mensagem;
import com.gerenciamento.backend.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mensagens")
public class MessageController {

    @Autowired
    private MensagemService mensagemService;
    @PostMapping
    public Mensagem createMensagem(@Valid @RequestBody Mensagem mensagem) {
        return mensagemService.createMensagem(mensagem);
    }

    @GetMapping
    public List<MensagemDTO> getAllMensagens() {
        return mensagemService.getAllMensagens().stream()
                .map(mensagem -> new MensagemDTO(mensagem.getConteudo(), mensagem.getDataEnvio()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensagemDTO> getMensagemById(@PathVariable(value = "id") Integer id) {
        Optional<Mensagem> mensagem = mensagemService.getMensagemById(id);
        return mensagem.map(m -> ResponseEntity.ok(new MensagemDTO(m.getConteudo(), m.getDataEnvio())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensagem> updateMensagem(@PathVariable(value = "id") Integer id, @Valid @RequestBody Mensagem mensagemDetails) {
        Optional<Mensagem> updatedMensagem = mensagemService.updateMensagem(id, mensagemDetails);
        return updatedMensagem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensagem(@PathVariable(value = "id") Integer id) {
        boolean isDeleted = mensagemService.deleteMensagem(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
