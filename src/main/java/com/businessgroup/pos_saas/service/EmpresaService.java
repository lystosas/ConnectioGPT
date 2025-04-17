package com.businessgroup.pos_saas.service;

import com.businessgroup.pos_saas.dto.EmpresaRequestDTO;
import com.businessgroup.pos_saas.dto.EmpresaResponseDTO;
import com.businessgroup.pos_saas.model.Empresa;
import com.businessgroup.pos_saas.repository.EmpresaRepository;
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
        empresaRepository.deleteById(id);
    }
}