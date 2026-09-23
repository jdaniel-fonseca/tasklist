package com.personal.tasklist.entitites;

import com.personal.tasklist.dto.request.TaskRequestDTO;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "TB_TASK")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Instant moment = Instant.now();
    private String annotation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {
    }

    public Task(TaskRequestDTO taskRequestDTO) {
        if (taskRequestDTO.getAnnotation() != null) {
            this.annotation = taskRequestDTO.getAnnotation();
        }
        this.moment = taskRequestDTO.getMoment();
        this.title = taskRequestDTO.getTitle();
    }

    public Task(Long id, String title, Instant moment, String annotation, User user) {
        this.id = id;
        this.title = title;
        this.moment = moment;
        this.annotation = annotation;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
