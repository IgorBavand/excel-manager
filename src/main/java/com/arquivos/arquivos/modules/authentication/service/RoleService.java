package com.arquivos.arquivos.modules.authentication.service;

import com.arquivos.arquivos.exception.NotFoundException;
import com.arquivos.arquivos.modules.authentication.dto.RoleRequest;
import com.arquivos.arquivos.modules.authentication.model.Role;
import com.arquivos.arquivos.modules.authentication.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository roleRepository) {
        this.repository = roleRepository;
    }

    public Role findByName(String name) {
        return repository.findByName(name).orElseThrow(
            () -> new NotFoundException("Permissão não exitente.")
        );
    }

    public List<Role> findAll() {
        return repository.findAll();
    }

    public Role save(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName().toUpperCase());
        return repository.save(role);
    }

}
