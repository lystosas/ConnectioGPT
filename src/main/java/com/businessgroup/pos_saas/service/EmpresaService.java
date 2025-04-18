package com.businessgroup.pos_saas.service;

import com.businessgroup.pos_saas.dto.EmpresaRequestDTO;
import com.businessgroup.pos_saas.dto.EmpresaResponseDTO;
import com.businessgroup.pos_saas.model.Empresa;
import com.businessgroup.pos_saas.repository.EmpresaRepository;
import com.businessgroup.pos_saas.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public EmpresaResponseDTO guardar(EmpresaRequestDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setNombre(dto.getNombre().toUpperCase());
        empresa.setNit(dto.getNit());
        empresa.setDireccion(dto.getDireccion().toUpperCase());
        empresa.setTelefono(dto.getTelefono());

        Empresa guardada = empresaRepository.save(empresa);

        return new EmpresaResponseDTO(
                guardada.getId(),
                guardada.getNombre(),
                guardada.getNit(),
                guardada.getDireccion(),
                guardada.getTelefono());
    }

    public List<EmpresaResponseDTO> listarTodas() {
        return empresaRepository.findAll().stream()
                .map(emp -> new EmpresaResponseDTO(
                        emp.getId(),
                        emp.getNombre(),
                        emp.getNit(),
                        emp.getDireccion(),
                        emp.getTelefono()))
                .collect(Collectors.toList());
    }

    public Optional<EmpresaResponseDTO> buscarPorId(UUID id) {
        return empresaRepository.findById(id)
                .map(emp -> new EmpresaResponseDTO(
                        emp.getId(),
                        emp.getNombre(),
                        emp.getNit(),
                        emp.getDireccion(),
                        emp.getTelefono()));
    }

    public void eliminar(UUID id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));

        boolean tieneUsuarios = usuarioRepository.existsByEmpresaId(id);

        if (tieneUsuarios) {
            throw new IllegalStateException("No se puede eliminar la empresa porque tiene usuarios asociados");
        }

        empresaRepository.delete(empresa);
    }

    public EmpresaResponseDTO actualizar(UUID id, EmpresaRequestDTO dto) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        empresa.setNombre(dto.getNombre().toUpperCase());
        empresa.setNit(dto.getNit().toUpperCase());
        empresa.setDireccion(dto.getDireccion().toUpperCase());
        empresa.setTelefono(dto.getTelefono().toUpperCase());

        Empresa actualizada = empresaRepository.save(empresa);

        return new EmpresaResponseDTO(
                actualizada.getId(),
                actualizada.getNombre(),
                actualizada.getNit(),
                actualizada.getDireccion(),
                actualizada.getTelefono());
    }

}