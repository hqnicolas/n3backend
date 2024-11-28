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

    public @Email(message = "Email deve ser válido") @NotBlank(message = "Email é obrigatório") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email deve ser válido") @NotBlank(message = "Email é obrigatório") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Senha é obrigatória") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Senha é obrigatória") String password) {
        this.password = password;
    }

    public @NotNull(message = "Data de nascimento é obrigatória") LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotNull(message = "Data de nascimento é obrigatória") LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotBlank(message = "Gênero é obrigatório") String getGender() {
        return gender;
    }

    public void setGender(@NotBlank(message = "Gênero é obrigatório") String gender) {
        this.gender = gender;
    }

    public @NotBlank(message = "Localização é obrigatória") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Localização é obrigatória") String location) {
        this.location = location;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }
}