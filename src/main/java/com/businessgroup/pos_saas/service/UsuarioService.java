package com.businessgroup.pos_saas.service;

import com.businessgroup.pos_saas.dto.UsuarioRequestDTO;
import com.businessgroup.pos_saas.dto.UsuarioResponseDTO;
import com.businessgroup.pos_saas.model.Empresa;
import com.businessgroup.pos_saas.model.Usuario;
import com.businessgroup.pos_saas.repository.EmpresaRepository;
import com.businessgroup.pos_saas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioResponseDTO> buscarPorId(UUID id) {
        return usuarioRepository.findById(id)
                .map(this::convertirAResponseDTO);
    }

    public UsuarioResponseDTO guardar(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre().toUpperCase());
        usuario.setCorreo(dto.getCorreo().toUpperCase());
        usuario.setClave(dto.getClave());

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        usuario.setEmpresa(empresa);

        Usuario guardado = usuarioRepository.save(usuario);
        return convertirAResponseDTO(guardado);
    }

    public void eliminar(UUID id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO convertirAResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getEmpresa().getNombre() // Mostramos el nombre de la empresa
        );
    }

    public UsuarioResponseDTO actualizar(UUID id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre().toUpperCase());
        usuario.setCorreo(dto.getCorreo().toUpperCase());
        usuario.setClave(dto.getClave());

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        usuario.setEmpresa(empresa);

        Usuario actualizado = usuarioRepository.save(usuario);
        return convertirAResponseDTO(actualizado);
    }

}