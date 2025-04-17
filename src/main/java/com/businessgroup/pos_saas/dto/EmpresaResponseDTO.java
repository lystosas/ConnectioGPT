package com.businessgroup.pos_saas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class EmpresaResponseDTO {
    private UUID id;
    private String nombre;
    private String nit;
    private String telefono;
    private String direccion;
}