package com.gerenciamento.backend.controller;
import com.gerenciamento.backend.dto.UserDTO;
import com.gerenciamento.backend.exception.EntityNotFoundException;
import com.gerenciamento.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UserDTO userDTO) {
        System.out.println("Solicitação de criação de usuário recebida: " + userDTO);
        try {
            UserDTO createdUser = userService.createUser(userDTO);
            System.out.println("Usuário criado com sucesso: " + createdUser);
            return new ResponseEntity<>(Collections.singletonMap("mensagem", "Usuário criado com sucesso!"), HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            System.err.println("Validação falhou: " + e.getMessage());
            Map<String, String> errors = new HashMap<>();
            for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
                System.err.println("Violação: " + violation.getPropertyPath() + " - " + violation.getMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.err.println("Erro ao criar usuário: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        System.out.println("Solicitação para receber todos os usuários");
        try {
            List<UserDTO> usersDTO = userService.getAllUsers();
            System.out.println("Usuários obtidos com sucesso: " + usersDTO);
            return new ResponseEntity<>(usersDTO, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuários: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        System.out.println("Solicitação para receber usuário por ID: " + id);
        try {
            UserDTO user = userService.getUserById(id);
            System.out.println("Usuário obtido com sucesso: " + user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.err.println("Usuário não encontrado: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        System.out.println("Solicitação de atualização de usuário por ID: " + id);
        try {
            UserDTO updatedUser = userService.updateUser(id, userDTO);
            System.out.println("Usuário atualizado com sucesso: " + updatedUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.err.println("Erro ao atualizar usuário por ID: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar usuário por ID: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        System.out.println("Solicitação de exclusão de usuário por ID: " + id);
        try {
            userService.deleteUser(id);
            System.out.println("Usuário excluído com sucesso: " + id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            System.err.println("Erro ao excluir usuário por ID: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}