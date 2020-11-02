package com.example.projectscoperest.repository;

import com.example.projectscoperest.model.Type;
import com.example.projectscoperest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findAllByType(Type type);
}
