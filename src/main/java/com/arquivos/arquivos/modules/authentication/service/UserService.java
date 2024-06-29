package com.arquivos.arquivos.modules.authentication.service;

import com.arquivos.arquivos.exception.NotFoundException;
import com.arquivos.arquivos.modules.authentication.model.Role;
import com.arquivos.arquivos.modules.authentication.model.User;
import com.arquivos.arquivos.modules.authentication.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User assignRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleService.findByName(roleName);

        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Usuário não encontrado.")
        );
    }
}

