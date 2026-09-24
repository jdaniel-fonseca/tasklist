package com.personal.tasklist.entitites;

import com.personal.tasklist.dto.auth.RegisterDTO;
import com.personal.tasklist.dto.request.UserRequestDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "TB_USER")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant createdIn = Instant.now();
    private String name;
    private Integer age;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Task> tasks = new HashSet<>();

    public User() {
    }

    public User(RegisterDTO registerDTO) {
        this.email = registerDTO.getEmail();
        this.password = registerDTO.getPassword();
        this.name = registerDTO.getName();
        this.age = registerDTO.getAge();
    }

    public User(Long id, String name, Integer age, String email, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(Instant createdIn) {
        this.createdIn = createdIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
