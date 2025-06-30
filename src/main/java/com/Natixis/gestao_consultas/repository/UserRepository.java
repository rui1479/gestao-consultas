package com.Natixis.gestao_consultas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Natixis.gestao_consultas.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
        List<User> getUsersByRole(String role);
        User findByUsername(String username);
}
