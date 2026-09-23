package com.personal.tasklist.controllers;
import com.personal.tasklist.dto.request.UserRequestDTO;
import com.personal.tasklist.dto.response.UserResponseDTO;
import com.personal.tasklist.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> findAll(@PageableDefault(size = 30) Pageable pageable) {
        Page<UserResponseDTO> userResponseDTOS = userService.findAll(pageable);
        return ResponseEntity.ok().body(userResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        UserResponseDTO userResponseDTO = userService.findById(id);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@RequestBody UserRequestDTO userRequestDTO, @PathVariable Long id) {
        UserResponseDTO userResponseDTO = userService.update(userRequestDTO, id);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
