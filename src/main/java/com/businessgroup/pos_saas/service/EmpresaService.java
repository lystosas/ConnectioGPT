package com.businessgroup.pos_saas.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.businessgroup.pos_saas.model.Empresa;
import com.businessgroup.pos_saas.repository.EmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarPorId(UUID id) {
        return empresaRepository.findById(id);
    }

    public Empresa guardar(Empresa empresa) {
        empresa.setNombre(empresa.getNombre().toUpperCase());
        empresa.setDireccion(empresa.getDireccion().toUpperCase());
        return empresaRepository.save(empresa);
    }

    public void eliminar(UUID id) {
        empresaRepository.deleteById(id);
    }
}
