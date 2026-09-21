package com.personal.tasklist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {

    private String title;
    private Instant moment = Instant.now();
    private String annotation;

}
