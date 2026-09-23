package com.personal.tasklist.repositories;

import com.personal.tasklist.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



}
