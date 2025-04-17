package com.businessgroup.pos_saas.controller;

import com.businessgroup.pos_saas.dto.ApiResponse;
import com.businessgroup.pos_saas.dto.EmpresaRequestDTO;
import com.businessgroup.pos_saas.dto.EmpresaResponseDTO;
import com.businessgroup.pos_saas.service.EmpresaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    public ResponseEntity<ApiResponse<EmpresaResponseDTO>> crear(@Valid @RequestBody EmpresaRequestDTO dto) {
        EmpresaResponseDTO nueva = empresaService.guardar(dto);
        return ResponseEntity
                .status(201)
                .body(new ApiResponse<>(201, "Empresa creada correctamente", nueva));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmpresaResponseDTO>>> listar() {
        List<EmpresaResponseDTO> empresas = empresaService.listarTodas();
        return ResponseEntity.ok(new ApiResponse<>(200, "Empresas obtenidas", empresas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmpresaResponseDTO>> obtenerPorId(@PathVariable UUID id) {
        return empresaService.buscarPorId(id)
                .map(empresa -> ResponseEntity.ok(new ApiResponse<>(200, "Empresa encontrada", empresa)))
                .orElse(ResponseEntity.status(404).body(new ApiResponse<>(404, "Empresa no encontrada", null)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable UUID id) {
        if (empresaService.buscarPorId(id).isPresent()) {
            empresaService.eliminar(id);
            return ResponseEntity.ok(new ApiResponse<>(200, "Empresa eliminada", null));
        }
        return ResponseEntity.status(404).body(new ApiResponse<>(404, "Empresa no encontrada", null));
    }
}