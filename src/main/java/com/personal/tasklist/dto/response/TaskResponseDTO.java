package com.personal.tasklist.dto.response;

import com.personal.tasklist.entitites.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class TaskResponseDTO {

    private String title;
    private Instant moment;
    private String annotation;
    private Long userId;

    public TaskResponseDTO(Task task) {
        this.title = task.getTitle();
        this.annotation = task.getAnnotation();
        this.moment = task.getMoment();
        this.userId = task.getUser().getId();
    }


}
