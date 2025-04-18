package com.businessgroup.pos_saas.controller;

import com.businessgroup.pos_saas.dto.CustomApiResponse;
import com.businessgroup.pos_saas.dto.EmpresaRequestDTO;
import com.businessgroup.pos_saas.dto.EmpresaResponseDTO;
import com.businessgroup.pos_saas.service.EmpresaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/empresas")
@SecurityRequirement(name = "bearerAuth")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<CustomApiResponse<EmpresaResponseDTO>> crear(@Valid @RequestBody EmpresaRequestDTO dto) {
        EmpresaResponseDTO nueva = empresaService.guardar(dto);
        return ResponseEntity
                .status(201)
                .body(new CustomApiResponse<>(201, "Empresa creada correctamente", nueva));
    }

    @GetMapping
    public ResponseEntity<CustomApiResponse<List<EmpresaResponseDTO>>> listar() {
        List<EmpresaResponseDTO> empresas = empresaService.listarTodas();
        return ResponseEntity.ok(new CustomApiResponse<>(200, "Empresas obtenidas", empresas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<EmpresaResponseDTO>> obtenerPorId(@PathVariable UUID id) {
        return empresaService.buscarPorId(id)
                .map(empresa -> ResponseEntity.ok(new CustomApiResponse<>(200, "Empresa encontrada", empresa)))
                .orElse(ResponseEntity.status(404).body(new CustomApiResponse<>(404, "Empresa no encontrada", null)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> eliminar(@PathVariable UUID id) {
        try {
            empresaService.eliminar(id);
            return ResponseEntity.ok(new CustomApiResponse<>(200, "Empresa eliminada correctamente", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(new CustomApiResponse<>(404, e.getMessage(), null));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(new CustomApiResponse<>(400, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<EmpresaResponseDTO>> actualizar(
            @PathVariable UUID id,
            @Valid @RequestBody EmpresaRequestDTO dto) {

        EmpresaResponseDTO actualizado = empresaService.actualizar(id, dto);
        return ResponseEntity.ok(new CustomApiResponse<>(200, "Empresa actualizada correctamente", actualizado));
    }

}