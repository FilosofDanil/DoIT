package com.example.backend.components.usercomponents;

import com.example.backend.DTOs.UserDTO;
import com.example.backend.components.interfaces.ComponentCrud;
import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserCRUDComponent implements ComponentCrud<UserDTO> {
    private final UserRepository userRepository;

    @Override
    public List<UserDTO> get(User user) {
        return null;
    }

    @Override
    public UserDTO getById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NullPointerException();
        }

        return UserMapper.toDto(userRepository.findById(id).get());
    }

    @Override
    public UserDTO create(UserDTO userDTO, User user) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id, User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    static class UserMapper {
        private static UserDTO toDto(User user) {
            return UserDTO.builder()
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .password(user.getPassword())
                    .profile_name(user.getProfile_name())
                    .surname(user.getSurname())
                    .birthDate(user.getBirthDate())
                    .build();
        }
    }
}
