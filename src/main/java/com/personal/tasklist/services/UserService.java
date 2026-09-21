package com.personal.tasklist.services;

import com.personal.tasklist.dto.response.UserResponseDTO;
import com.personal.tasklist.entitites.User;
import com.personal.tasklist.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponseDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserResponseDTO::new);
    }

}
