package com.personal.tasklist.services;
import com.personal.tasklist.dto.request.TaskRequestDTO;
import com.personal.tasklist.dto.response.TaskResponseDTO;
import com.personal.tasklist.entitites.Task;
import com.personal.tasklist.exceptions.NotFoundException;
import com.personal.tasklist.repositories.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<TaskResponseDTO> findAll(Pageable pageable) {
        return taskRepository
                .findAll(pageable)
                .map(TaskResponseDTO::new);
    }

    public TaskResponseDTO findById(Long id) {
        return taskRepository
                .findById(id)
                .map(TaskResponseDTO::new)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public TaskResponseDTO create(TaskRequestDTO taskRequestDTO) {
        Task task = new Task(taskRequestDTO);
        Task savedTask = taskRepository.save(task);
        return new TaskResponseDTO(savedTask);
    }

    public TaskResponseDTO update(TaskRequestDTO taskRequestDTO, Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        updateData(taskRequestDTO, task);
        return new TaskResponseDTO(task);
    }

    public void deleteById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        taskRepository.delete(task);
    }

    private void updateData(TaskRequestDTO taskRequestDTO, Task task) {
        task.setTitle(taskRequestDTO.getTitle());
        task.setMoment(taskRequestDTO.getMoment());
        task.setAnnotation(taskRequestDTO.getAnnotation());
    }
}
