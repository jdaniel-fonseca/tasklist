package com.personal.tasklist.controllers;

import com.personal.tasklist.dto.auth.RegisterDTO;
import com.personal.tasklist.dto.response.UserResponseDTO;
import com.personal.tasklist.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody RegisterDTO registerDTO) {
        UserResponseDTO userResponseDTO = registerService.create(registerDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userResponseDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(userResponseDTO);
    }
}
