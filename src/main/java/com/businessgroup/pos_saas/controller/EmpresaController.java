package com.businessgroup.pos_saas.controller;

import com.businessgroup.pos_saas.dto.ApiResponse;
import com.businessgroup.pos_saas.model.Empresa;
import com.businessgroup.pos_saas.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Empresa>>> listar() {
        List<Empresa> empresas = empresaService.listarTodas();
        return ResponseEntity.ok(new ApiResponse<>(200, "Lista de empresas obtenida correctamente", empresas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Empresa>> obtenerPorId(@PathVariable UUID id) {
        return empresaService.buscarPorId(id)
                .map(empresa -> ResponseEntity.ok(new ApiResponse<>(200, "Empresa encontrada", empresa)))
                .orElse(ResponseEntity.status(404).body(new ApiResponse<>(404, "Empresa no encontrada", null)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Empresa>> crear(@Valid @RequestBody Empresa empresa) {
        Empresa guardada = empresaService.guardar(empresa);
        return ResponseEntity.status(201).body(new ApiResponse<>(201, "Empresa creada exitosamente", guardada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable UUID id) {
        if (empresaService.buscarPorId(id).isPresent()) {
            empresaService.eliminar(id);
            return ResponseEntity.ok(new ApiResponse<>(200, "Empresa eliminada correctamente", null));
        }
        return ResponseEntity.status(404).body(new ApiResponse<>(404, "Empresa no encontrada", null));
    }
}
