package com.businessgroup.pos_saas.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.businessgroup.pos_saas.model.Usuario;
import com.businessgroup.pos_saas.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardar(Usuario usuario) {
        usuario.setNombre(usuario.getNombre().toUpperCase());
        usuario.setCorreo(usuario.getCorreo().toUpperCase());
        // No convertimos la clave por seguridad (ni idea si luego se encripta)
        return usuarioRepository.save(usuario);
    }

    public void eliminar(UUID id) {
        usuarioRepository.deleteById(id);
    }
}
