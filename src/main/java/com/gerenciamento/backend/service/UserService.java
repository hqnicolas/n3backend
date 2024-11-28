package com.gerenciamento.backend.service;
import com.gerenciamento.backend.dto.UserDTO;
import com.gerenciamento.backend.exception.EntityNotFoundException;
import com.gerenciamento.backend.model.User;
import com.gerenciamento.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        try {
            User user = mapToUser(userDTO);
            User savedUser = userRepository.save(user);
            return mapToUserDTO(savedUser);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar usuário: " + e.getMessage(), e);
        }
    }

    public List<UserDTO> getAllUsers() {
        try {
            return userRepository.findAll().stream()
                    .map(this::mapToUserDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter usuários: " + e.getMessage(), e);
        }
    }

    public UserDTO getUserById(Long id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));
            return mapToUserDTO(user);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter usuário por ID: " + e.getMessage(), e);
        }
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));
            existingUser.setName(userDTO.getName());
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setPassword(userDTO.getPassword());
            existingUser.setDateOfBirth(userDTO.getDateOfBirth());
            existingUser.setGender(userDTO.getGender());
            existingUser.setLocation(userDTO.getLocation());
            existingUser.setPreferences(userDTO.getPreferences());
            existingUser.setBiography(userDTO.getBiography());
            existingUser.setPhotos(userDTO.getPhotos());
            existingUser.setInterests(userDTO.getInterests());
            User updatedUser = userRepository.save(existingUser);
            return mapToUserDTO(updatedUser);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar usuário por ID: " + e.getMessage(), e);
        }
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir usuário por ID: " + e.getMessage(), e);
        }
    }

    private User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setGender(userDTO.getGender());
        user.setLocation(userDTO.getLocation());
        user.setPreferences(userDTO.getPreferences());
        user.setBiography(userDTO.getBiography());
        user.setPhotos(userDTO.getPhotos());
        user.setInterests(userDTO.getInterests());
        return user;
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setGender(user.getGender());
        userDTO.setLocation(user.getLocation());
        userDTO.setPreferences(user.getPreferences());
        userDTO.setBiography(user.getBiography());
        userDTO.setPhotos(user.getPhotos());
        userDTO.setInterests(user.getInterests());
        return userDTO;
    }
}