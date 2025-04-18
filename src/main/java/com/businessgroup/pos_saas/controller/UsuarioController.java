package com.businessgroup.pos_saas.controller;

import com.businessgroup.pos_saas.dto.CustomApiResponse;
import com.businessgroup.pos_saas.dto.UsuarioRequestDTO;
import com.businessgroup.pos_saas.dto.UsuarioResponseDTO;
import com.businessgroup.pos_saas.dto.UsuarioUpdateRequestDTO;
import com.businessgroup.pos_saas.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<CustomApiResponse<List<UsuarioResponseDTO>>> listar() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(new CustomApiResponse<>(200, "Usuarios listados correctamente", usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<UsuarioResponseDTO>> obtenerPorId(@PathVariable UUID id) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> ResponseEntity.ok(new CustomApiResponse<>(200, "Usuario encontrado", usuario)))
                .orElse(ResponseEntity.status(404).body(new CustomApiResponse<>(404, "Usuario no encontrado", null)));
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<UsuarioResponseDTO>> crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO creado = usuarioService.guardar(dto);
        return ResponseEntity.status(201).body(new CustomApiResponse<>(201, "Usuario creado correctamente", creado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> eliminar(@PathVariable UUID id) {
        usuarioService.eliminar(id);
        return ResponseEntity.ok(new CustomApiResponse<>(200, "Usuario eliminado correctamente", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<UsuarioResponseDTO>> actualizar(@PathVariable UUID id,
            @Valid @RequestBody UsuarioUpdateRequestDTO dto) {

        UsuarioResponseDTO actualizado = usuarioService.actualizar(id, dto);
        return ResponseEntity.ok(new CustomApiResponse<>(200, "Usuario actualizado correctamente", actualizado));
    }

    @Operation(summary = "Cambiar empresa de un usuario", description = "Asigna una nueva empresa a un usuario existente mediante su ID y el ID de la empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa del usuario actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario o empresa no encontrados")
    })
    @PatchMapping("/{id}/empresa/{empresaId}")
    public ResponseEntity<CustomApiResponse<UsuarioResponseDTO>> cambiarEmpresa(
            @PathVariable UUID id,
            @PathVariable UUID empresaId) {

        UsuarioResponseDTO actualizado = usuarioService.cambiarEmpresa(id, empresaId);
        return ResponseEntity
                .ok(new CustomApiResponse<>(200, "Empresa del usuario actualizada correctamente", actualizado));
    }

}