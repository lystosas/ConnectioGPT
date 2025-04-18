package com.businessgroup.pos_saas.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioUpdateRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser válido")
    private String correo;

    private String clave; // ✅ opcional, solo si se desea cambiar

    @NotNull(message = "La empresa es obligatoria")
    private UUID empresaId;
}
