package com.personal.tasklist.controllers;

import com.personal.tasklist.dto.request.TaskRequestDTO;
import com.personal.tasklist.dto.response.TaskResponseDTO;
import com.personal.tasklist.services.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/tasks")
@SecurityRequirement(name = "bearer-key")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> findAll(@PageableDefault(size = 30) Pageable pageable) {
        Page<TaskResponseDTO> taskResponseDTOS = taskService.findAll(pageable);
        return ResponseEntity.ok().body(taskResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long id) {
        TaskResponseDTO taskResponseDTO = taskService.findById(id);
        return ResponseEntity.ok().body(taskResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(@RequestBody TaskRequestDTO taskRequestDTO, @PathVariable Long id) {
        TaskResponseDTO taskResponseDTO = taskService.update(taskRequestDTO, id);
        return ResponseEntity.ok().body(taskResponseDTO);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO taskResponseDTO = taskService.create(taskRequestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskResponseDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(taskResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
