package com.personal.tasklist.dto.response;

import com.personal.tasklist.entitites.Task;
import com.personal.tasklist.entitites.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private Instant createdIn = Instant.now();
    private String name;
    private Integer age;
    private String email;

    private Set<TaskResponseDTO> tasks = new HashSet<>();

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.age = user.getAge();
        this.createdIn = user.getCreatedIn();
        this.email = user.getEmail();

        if (user.getTasks() != null) {
            for (Task t : user.getTasks()) {
                this.tasks.add(new TaskResponseDTO(t));
            }
        }
    }
}
