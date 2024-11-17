package com.gerenciamento.backend.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String password;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gênero é obrigatório")
    private String gender;

    @NotBlank(message = "Localização é obrigatória")
    private String location;

    private String preferences;
    private String biography;
    private String photos;
    private String interests;
}