package com.example.projectscopemvs.repository;

import com.example.projectscopemvs.model.Type;
import com.example.projectscopemvs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findAllByType(Type type);
}
