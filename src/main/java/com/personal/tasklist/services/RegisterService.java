package com.personal.tasklist.services;

import com.personal.tasklist.dto.auth.RegisterDTO;
import com.personal.tasklist.dto.response.UserResponseDTO;
import com.personal.tasklist.entitites.User;
import com.personal.tasklist.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    public UserRepository userRepository;
    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO create(RegisterDTO registerDTO) {
        User user = userRepository.save(requestConverter(registerDTO));
        return new UserResponseDTO(user);
    }

    public User requestConverter(RegisterDTO registerDTO) {
        User user = new User();
        user.setPassword(registerDTO.getPassword());
        user.setAge(registerDTO.getAge());
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        return user;
    }

}
