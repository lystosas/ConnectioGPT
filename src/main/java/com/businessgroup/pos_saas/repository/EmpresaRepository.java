package com.businessgroup.pos_saas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.businessgroup.pos_saas.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {
    boolean existsByNit(String nit);
}
