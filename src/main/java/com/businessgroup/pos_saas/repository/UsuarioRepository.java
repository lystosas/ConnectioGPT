package com.businessgroup.pos_saas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.businessgroup.pos_saas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    boolean existsByCorreo(String correo);
}
