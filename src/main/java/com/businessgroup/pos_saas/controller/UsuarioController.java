package com.businessgroup.pos_saas.controller;

import com.businessgroup.pos_saas.dto.ApiResponse;
import com.businessgroup.pos_saas.dto.UsuarioRequestDTO;
import com.businessgroup.pos_saas.dto.UsuarioResponseDTO;
import com.businessgroup.pos_saas.service.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> listar() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(new ApiResponse<>(200, "Usuarios listados correctamente", usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> obtenerPorId(@PathVariable UUID id) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> ResponseEntity.ok(new ApiResponse<>(200, "Usuario encontrado", usuario)))
                .orElse(ResponseEntity.status(404).body(new ApiResponse<>(404, "Usuario no encontrado", null)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO creado = usuarioService.guardar(dto);
        return ResponseEntity.status(201).body(new ApiResponse<>(201, "Usuario creado correctamente", creado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable UUID id) {
        usuarioService.eliminar(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Usuario eliminado correctamente", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> actualizar(
            @PathVariable UUID id,
            @Valid @RequestBody UsuarioRequestDTO dto) {

        UsuarioResponseDTO actualizado = usuarioService.actualizar(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(200, "Usuario actualizado correctamente", actualizado));
    }

}