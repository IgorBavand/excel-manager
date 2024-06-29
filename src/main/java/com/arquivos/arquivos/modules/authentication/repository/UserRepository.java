package com.arquivos.arquivos.modules.authentication.repository;

import com.arquivos.arquivos.modules.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
