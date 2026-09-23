package com.personal.tasklist.services;
import com.personal.tasklist.dto.request.UserRequestDTO;
import com.personal.tasklist.dto.response.UserResponseDTO;
import com.personal.tasklist.entitites.User;
import com.personal.tasklist.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponseDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserResponseDTO::new);
    }

    public UserResponseDTO findById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDTO::new)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    public UserResponseDTO update(UserRequestDTO userRequestDTO, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found."));

        User updatedUser = updateData(userRequestDTO, user);
        return new UserResponseDTO(updatedUser);
    }

    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found."));

        userRepository.delete(user);
    }

    private User updateData(UserRequestDTO userRequestDTO, User user) {
        user.setAge(userRequestDTO.getAge());
        user.setName(userRequestDTO.getName());
        return user;
    }
}
